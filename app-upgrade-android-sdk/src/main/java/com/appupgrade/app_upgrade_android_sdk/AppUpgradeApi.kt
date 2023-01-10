package com.appupgrade.app_upgrade_android_sdk

import com.appupgrade.app_upgrade_android_sdk.models.AppUpgradeResponse
import retrofit2.Call
import retrofit2.http.GET;
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface AppUpgradeApi {
    companion object {
        const val BASE_URL = "https://appupgrade.dev/"
    }

    @Headers("sdk: android-kotlin")
    @GET("api/v1/versions/check")
    fun versionCheck(@Header("x-api-key") xApiKey: String, @QueryMap params: Map<String, String>): Call<AppUpgradeResponse>
}