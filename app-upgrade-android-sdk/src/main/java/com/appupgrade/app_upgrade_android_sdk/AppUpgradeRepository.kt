    package com.appupgrade.app_upgrade_android_sdk

    import android.app.Activity
    import android.app.AlertDialog
    import android.content.ActivityNotFoundException
    import android.content.DialogInterface
    import android.content.Intent
    import android.net.Uri
    import android.util.Log
    import android.widget.Button
    import com.appupgrade.app_upgrade_android_sdk.models.AlertDialogConfig
    import com.appupgrade.app_upgrade_android_sdk.models.AppInfo
    import com.appupgrade.app_upgrade_android_sdk.models.AppUpgradeResponse
    import retrofit2.Call
    import retrofit2.Callback
    import retrofit2.Response
    import retrofit2.Retrofit;
    import retrofit2.converter.gson.GsonConverterFactory;

    class AppUpgradeRepository: AppUpgradeService {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(AppUpgradeApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private val appUpgradeApi by lazy {
            retrofit.create(AppUpgradeApi::class.java)
        }

        override fun checkForUpdates(parentActivity: Activity, xApiKey: String, appInfo: AppInfo, alertDialogConfig: AlertDialogConfig?) {
            try {
                val params: MutableMap<String, String> = HashMap()
                params["app_name"] = appInfo.appName
                params["app_version"] = appInfo.appVersion
                params["platform"] = appInfo.platform
                params["environment"] = appInfo.environment

                if (!appInfo.appLanguage.isNullOrEmpty()) {
                    params["app_language"] = appInfo.appLanguage
                }

                val call = appUpgradeApi.versionCheck(xApiKey, params)

                call.enqueue(object : Callback<AppUpgradeResponse> {
                    override fun onFailure(call: Call<AppUpgradeResponse>, t: Throwable) {
                        // Handle the failure
                        Log.d("App Upgrade: ", t.localizedMessage)
                    }

                    override fun onResponse(call: Call<AppUpgradeResponse>, response: Response<AppUpgradeResponse>) {
                        if (response.code() == 200) {
                            val appUpgradeResponse = response.body()!!

                            if (appUpgradeResponse.found) {
                                Log.d("App Upgrade: ", "Version found, Update required.");
                                if (appUpgradeResponse.forceUpgrade) {
                                    Log.d("App Upgrade: ", "Force Update required.");
                                    showForceUpgradePopup(parentActivity, appInfo.appId, appUpgradeResponse.message, alertDialogConfig);
                                } else {
                                    Log.d(
                                        "App Upgrade: ",
                                        "Force Update is not required but update is recommended."
                                    );
                                    showUpgradePopup(parentActivity, appInfo.appId, appUpgradeResponse.message, alertDialogConfig);
                                }
                            } else {
                                Log.d("App Upgrade: ", "Version not found, Update not required.");
                            }
                        } else {
                            Log.d("App Upgrade: ", "Request failed with http error code: ${response.code()}")
                        }
                    }
                })
            } catch (e: Exception) {
                Log.d("App Upgrade: ", e.localizedMessage)
            }

        }

        fun showForceUpgradePopup(parentActivity: Activity, appId: String, updateMessage: String, alertDialogConfig: AlertDialogConfig?) {
            Log.d("App Upgrade: ", "Show force upgrade popup.")

            if (!parentActivity.isFinishing) {
                parentActivity.runOnUiThread {
                    val builder = AlertDialog.Builder(parentActivity)
                    builder.setMessage(updateMessage)
                    builder.setCancelable(false)

                    var updateButtonTitle = "Update Now"
                    if (alertDialogConfig != null) {
                        updateButtonTitle = alertDialogConfig.updateButtonTitle ?: updateButtonTitle
                    }

                    builder.setPositiveButton(updateButtonTitle, null)

                    val alert = builder.create()

                    alert.setOnShowListener {
                        val b: Button = alert.getButton(android.app.AlertDialog.BUTTON_POSITIVE)
                        b.setOnClickListener { onUserUpdate(parentActivity, appId) }
                    }

                    var alertDialogTitle = "Please Update"
                    if (alertDialogConfig != null) {
                        alertDialogTitle = alertDialogConfig.title ?: alertDialogTitle
                    }
                    alert.setTitle(alertDialogTitle)
                    alert.show()
                }
            } else {
                Log.d("App Upgrade Err: ", "Parent activity is finished running.")
            }
        }

        fun showUpgradePopup(parentActivity: Activity, appId: String, updateMessage: String, alertDialogConfig: AlertDialogConfig?) {
            Log.d("App Upgrade: ", "Show upgrade popup.")

            if (!parentActivity.isFinishing) {
                parentActivity.runOnUiThread {
                    val builder = AlertDialog.Builder(parentActivity)
                    builder.setMessage(updateMessage)
                    builder.setCancelable(true)

                    var updateButtonTitle = "Update Now"
                    if (alertDialogConfig != null) {
                        updateButtonTitle = alertDialogConfig.updateButtonTitle ?: updateButtonTitle
                    }

                    builder.setPositiveButton(updateButtonTitle,
                        DialogInterface.OnClickListener { dialog: DialogInterface, which: Int ->
                            onUserUpdate(parentActivity, appId)
                            // If user click Later then dialog box is canceled.
                            dialog.cancel()
                        } as DialogInterface.OnClickListener)

                    var laterButtonTitle = "Later"
                    if (alertDialogConfig != null) {
                        laterButtonTitle = alertDialogConfig.laterButtonTitle ?: laterButtonTitle
                    }
                    builder.setNegativeButton(laterButtonTitle,
                        DialogInterface.OnClickListener { dialog: DialogInterface, which: Int ->
                            onUserLater()
                            // If user click Later then dialog box is canceled.
                            dialog.cancel()
                        } as DialogInterface.OnClickListener)

                    val alert = builder.create()
                    var alertDialogTitle = "Please Update"
                    if (alertDialogConfig != null) {
                        alertDialogTitle = alertDialogConfig.title ?: alertDialogTitle
                    }
                    alert.setTitle(alertDialogTitle)
                    alert.show()
                }
            } else {
                Log.d("App Upgrade Err: ", "Parent activity is finished running.")
            }
        }

        private fun onUserLater() {
            Log.d("App Upgrade: ", "Later.")
        }

        private fun onUserUpdate(parentActivity: Activity, appId: String) {
            Log.d("App Upgrade: ", "Update Now.")
            try {
                parentActivity.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$appId")
                    )
                )
            } catch (anfe: ActivityNotFoundException) {
                parentActivity.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$appId")
                    )
                )
            }
        }
    }