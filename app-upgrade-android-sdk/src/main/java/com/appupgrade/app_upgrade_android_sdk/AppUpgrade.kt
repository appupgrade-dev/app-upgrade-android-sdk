package com.appupgrade.app_upgrade_android_sdk

import android.app.Activity
import android.util.Log
import com.appupgrade.app_upgrade_android_sdk.models.AlertDialogConfig
import com.appupgrade.app_upgrade_android_sdk.models.AppInfo

class AppUpgrade {
    private val appUpgradeRepository by lazy {
        AppUpgradeRepository()
    }

    @JvmOverloads fun checkForUpdates(parentActivity: Activity, xApiKey: String, appInfo: AppInfo, alertDialogConfig: AlertDialogConfig?=null) {
        Log.i("App Upgrade: ", "Checking for updates")
        appUpgradeRepository.checkForUpdates(parentActivity, xApiKey, appInfo, alertDialogConfig)
    }
}