package com.projects.androidnewapis

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.NotificationCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.projects.androidnewapis.newsplashapi.SplashViewModel
import com.projects.androidnewapis.newsplashapi.WelcomeSplash
import com.projects.androidnewapis.notificationsapi33.ShowNotificationUI
import com.projects.androidnewapis.permissionsapi.DisplayRationale
import com.projects.androidnewapis.ui.theme.AndroidNewAPIsTheme

class MainActivity : ComponentActivity() {

    private val viewModel = SplashViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Uncomment this lines to see splash screen
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }

        setContent {
            WelcomeSplash()
            DisplayRationale()
            ShowNotificationUI {
                showNotification()
            }
        }
    }

    private fun showNotification() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(this, "channel_id")
            .setContentText("This is some Content Text")
            .setContentTitle("Hello User!")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()

        notificationManager.notify(1, notification)
    }
}
