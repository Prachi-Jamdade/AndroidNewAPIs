## Exploring New android APIs


# 1. New Splash Screen API in Android 12

Starting in Android 12, the launch animation for all apps when running on a device with Android 12 or higher. This includes an into-app motion at launch, a splash screen showing your app icon, and a transition to your app itself.

This experience brings standard design elements to every app launch, but it’s also customizable so your app can maintain its unique branding.

In addition to using the ```SplashScreen``` platform API, you can also use the ```SplashScreen``` compat library, which wraps the SplashScreen API.


## Splash Screen animations and the launch sequence
Additional latency is often associated with launching an app on a cold start. Adding an animated icon to your splash screen has obvious aesthetic appeal and provides a more premium experience, plus there’s an additional benefit: user research shows that perceived startup time is less when viewing an animation.

A splash screen animation is embedded within the following launch sequence components.

**Enter animation**: This consists of the system view to the splash screen. This is controlled by the system and is not customizable.

**Splash screen**: The splash screen can be customized, allowing you to supply your own logo animation and branding. It must meet the requirements described in this document to work properly.

**Exit animation**: This consists of the animation run that hides the splash screen. If you want to customize it, you'll have access to the SplashScreenView and its icon and can run any animation on them, with settings for transform, opacity, and color. In that case, the splash screen needs to be manually removed when the animation is done.

When running the icon animation, app launch gives you the option to skip the sequence in cases where the app is ready earlier. Either the app triggers ```onResume()``` or the splash screen times out automatically so make sure the motion can be comfortably skipped. The splash screen should only be dismissed with ```onResume()``` when the app is stable from a visual standpoint, so no additional spinners needed. Introducing an incomplete interface can be jarring for users and may give an impression of unpredictability or lack of polish.

For detailed info visit official doc - https://developer.android.com/develop/ui/views/launch/splash-screen

# 2. Notification Runtime Permission in Android 13

Android 13 (API level 33) and higher supports a runtime permission for sending non-exempt (including Foreground Services (FGS)) notifications from an app: ```POST_NOTIFICATIONS```. This change helps users focus on the notifications that are most important to them.

If you continue to target 12L (API level 32) or lower, you lose some flexibility with requesting the permission in the context of your app's functionality.

## Declare the Permission

The permission that you need to declare in your app's manifest file appears in the following code snippet:

```
<manifest ...>
    <uses-permission android:name="android.permission.POST_NOTIFICATIONS"/>
    <application ...>
        ...
    </application>
</manifest>
```

## App capabilities depend on user choice in permissions dialog
In this dialog, users have the following actions available to them:

**Select allow** 
**Select don't allow**
**Swipe away from the dialog, without pressing either button**
The following sections describe how your app behaves, based on which action the user takes.

**User selects "Allow"**
If the user selects the allow option, your app can do the following:

1. Send notifications. All notification channels are allowed.
2. Post notifications related to foreground services. These notifications appear in the notification drawer.

**User selects "Don't allow"**
If the user selects the don't allow option, your app can't send notifications unless it qualifies for an exemption. All notification channels are blocked, except for a few specific roles. This is similar to the behavior that occurs when the user manually turns off all notifications for your app in system settings.

🛑 Caution: If your app targets 12L or lower and the user taps Don't allow, even just once, they aren't prompted again until one of the following occurs:
The user uninstalls and reinstalls your app.
You update your app to target Android 13 or higher.

**User swipes away from dialog**
If the user swipes away from the dialog—that is, they don't select either allow or don't allow—the state of the notification permission doesn't change.

Click here to know effects of this runtime notification on apps - https://developer.android.com/develop/ui/views/notifications/notification-permission

# 3. Jetpack Compose Permissions

A library which provides Android runtime permissions support for Jetpack Compose.

⚠ Warning

The permission APIs are currently experimental and they could change at any time. All of the APIs are marked with the ```@ExperimentalPermissionsApi``` annotation.

Usage
```rememberPermissionState``` and ```rememberMultiplePermissionsState``` APIs
The ```rememberPermissionState(permission: String)``` API allows you to request a certain permission to the user and check for the status of the permission. ```rememberMultiplePermissionsState(permissions: List<String>)``` offers the same but for multiple permissions at the same time.

Both APIs expose properties for you to follow the workflow as described in the permissions documentation.

🛑 Caution: The call to the method that requests the permission to the user (e.g. ```PermissionState.launchPermissionRequest()```) needs to be invoked from a non-composable scope. For example, from a side-effect or from a non-composable callback such as a Button's ```onClick``` lambda.

Visit here for more info - https://google.github.io/accompanist/permissions/


