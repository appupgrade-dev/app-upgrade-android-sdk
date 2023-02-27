package com.appupgrade.app_upgrade_android_sdk.models

data class AppInfo(
    val appId: String,
    val appName: String,
    val appVersion: String,
    val platform: String,
    val environment: String,
    val appLanguage: String? = null,
    val preferredAndroidMarket: String? = null,
    val otherAndroidMarketUrl: String? = null,
)
