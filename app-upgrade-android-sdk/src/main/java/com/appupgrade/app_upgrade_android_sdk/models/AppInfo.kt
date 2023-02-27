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
) {
    constructor(
        appId: String,
        appName: String,
        appVersion: String,
        platform: String,
        environment: String,
    ): this(appId, appName, appVersion, platform, environment, null, null, null)

    constructor(
        appId: String,
        appName: String,
        appVersion: String,
        platform: String,
        environment: String,
        appLanguage: String,
    ): this(appId, appName, appVersion, platform, environment, appLanguage, null, null)

    constructor(
        appId: String,
        appName: String,
        appVersion: String,
        platform: String,
        environment: String,
        appLanguage: String,
        preferredAndroidMarket: String?,
    ): this(appId, appName, appVersion, platform, environment, appLanguage, preferredAndroidMarket, null)
}
