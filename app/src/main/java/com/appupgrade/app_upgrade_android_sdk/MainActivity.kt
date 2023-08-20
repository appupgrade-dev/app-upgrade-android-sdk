package com.appupgrade.app_upgrade_android_sdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appupgrade.app_upgrade_android_sdk.models.AlertDialogConfig
import com.appupgrade.app_upgrade_android_sdk.models.AppInfo
import com.appupgrade.app_upgrade_android_sdk.models.PreferredAndroidMarket

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val xApiKey = "ZWY0ZDhjYjgtYThmMC00NTg5LWI0NmUtMjM5OWZkNjkzMzQ5"

        val appInfo = AppInfo(
            appId = "com.android.chrome",
            appName = "Wallpaper app",
            appVersion = "1.0.0",
            platform = "android",
            environment = "production",
            appLanguage = "en",
            // preferredAndroidMarket = PreferredAndroidMarket.HUAWEI, // or PreferredAndroidMarket.AMAZON or PreferredAndroidMarket.OTHER If not provided default is Google playstore. Optional
            // otherAndroidMarketUrl = "https://otherandroidmarketurl.com/app/id" // Required if preferredAndroidMarket is OTHER.
//            customAttributes = mapOf(
//                "os" to 12,
//                "country" to "IN",
//                "build" to 100
//            )
        )

        AppUpgrade().checkForUpdates(this, xApiKey, appInfo);
    }
}