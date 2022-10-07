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
