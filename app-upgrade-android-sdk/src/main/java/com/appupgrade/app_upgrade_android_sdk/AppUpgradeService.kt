package com.appupgrade.app_upgrade_android_sdk

import android.app.Activity
import com.appupgrade.app_upgrade_android_sdk.models.AlertDialogConfig
import com.appupgrade.app_upgrade_android_sdk.models.AppInfo

interface AppUpgradeService {
    fun checkForUpdates(parentActivity: Activity, xApiKey: String, appInfo: AppInfo, alertDialogConfig: AlertDialogConfig?)
}