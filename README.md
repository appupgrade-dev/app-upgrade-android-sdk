# App Upgrade: Android SDK

Android SDK for [App Upgrade](https://appupgrade.dev)

App Upgrade is a service let your users know when to upgrade your apps or force them to upgrade the app.

[![Twitter](https://img.shields.io/twitter/follow/app_upgrade?style=social)](https://twitter.com/app_upgrade)
[![YouTube](https://img.shields.io/youtube/channel/subscribers/UC0ZVJPYHFVuMwEsro4VZKXw?style=social)](https://www.youtube.com/channel/UC0ZVJPYHFVuMwEsro4VZKXw)

Many times we need to force upgrade mobile apps on users' mobile. Having faced this issue multiple times decided to find a better way to tackle this problem. After doing some research on how people are doing this there are so many custom solutions or checking with the play store or AppStore API if there is a new version available. Although this works if we just want to nudge users that there is a new version available. It doesn't solve the problem where we want to make a decision.. whether it's a soft graceful update or we want to force update. So here is this product that will make developers' life easy. We can set custom messages.. see the versions in beautify dashboard, and many exciting features in the roadmap ahead.

This SDK communicate with App Upgrade and check the version with store version information in App Upgrade. Based on response it will:
- If app needs force update will show a non-dismissable popup for update. On click it will launch app in app store for user to update.
- If app needs to be updated but not a force update, it will show a dismissable popup.
- If no action is required it won't do anything.

## Installation
Install via gradle

1. Add the JitPack repository to your settings.gradle file
```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        ...
        maven { url "https://jitpack.io" }
    }
}
```
If you are using gradel version prior to 7.x.x add the following in your build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

2. Add the dependency to your project
```groovy
dependencies {
  implementation 'com.github.appupgrade-dev:app-upgrade-android-sdk:1.0.1'
}
```

## How to use it.
1. Register on App Upgrade and follow the instructions to create project and get the x-api-key.
2. Use the SDK.

#### Kotlin

```kotlin
val xApiKey = "ZWY0ZDhjYjgtYThmMC00NTg5LWI0NmUtMjM5OWZkNjkzMzQ5"

val appInfo = AppInfo(
    appId = "com.android.com",
    appName = "Wallpaper app",
    appVersion = "1.0.0",
    platform = "android",
    environment = "production"
)

//Optional
val alertDialogConfig = AlertDialogConfig(
    title = "Update Required", //Default: Please Update
    updateButtonTitle = "Update Now", //Default: Update Now
    laterButtonTitle = "Not Now" //Default: Later
)

val appUpgrade = AppUpgrade()
appUpgrade.checkForUpdates(this, xApiKey, appInfo, alertDialogConfig)
```

#### Java
```java
String xApiKey = "ZWY0ZDhjYjgtYThmMC00NTg5LWI0NmUtMjM5OWZkNjkzMzQ5";

String appId = "com.android.com";
String appName = "Wallpaper app";
String appVersion = "1.0.0";
String platform = "android";
String environment = "production";

AppInfo appInfo = new AppInfo(appId, appName, appVersion, platform, environment);

String title = "Update Required";
String updateButtonTitle = "UPDATE";
String laterButtonTitle = "Not Now";

AlertDialogConfig alertDialogConfig = new AlertDialogConfig(title, updateButtonTitle, laterButtonTitle);

AppUpgrade appUpgrade = new AppUpgrade();
appUpgrade.checkForUpdates(this, xApiKey, appInfo, alertDialogConfig);
```

### Note:
2. For opening the app store or play store the app should be live.
3. It might not be able to open the app store or play store in simulator. You can try it in physical device.


## Screenshot
 ![forceupgrade_android_kotlin](https://raw.githubusercontent.com/appupgrade-dev/app-upgrade-assets/main/images/forceupgrade_android_kotlin.png)

## App Upgrade Docs
For more information visit [App Upgrade](https://appupgrade.dev)

### Changelog

Please see [CHANGELOG](CHANGELOG.md) for more information what has changed recently.

### Contributing

Please see [CONTRIBUTING](CONTRIBUTING.md) and [CODE OF CONDUCT](CODE_OF_CONDUCT.md) for details.

### License

The MIT License (MIT). Please see [License File](LICENSE) for more information.

## Need help?

If you're looking for help, try our [Documentation](https://appupgrade.dev/docs/) or our [FAQ](https://appupgrade.dev/docs/app-upgrade-faq).
If you need support please write to us at appupgrade.dev@gmail.com

### Happy Coding!!!
