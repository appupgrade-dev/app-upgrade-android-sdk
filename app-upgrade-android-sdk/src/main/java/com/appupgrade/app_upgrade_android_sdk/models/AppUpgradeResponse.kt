package com.appupgrade.app_upgrade_android_sdk.models

data class AppUpgradeResponse(
    val found: Boolean,
    val forceUpgrade: Boolean,
    val message: String,
)