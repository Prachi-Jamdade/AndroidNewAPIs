package com.projects.androidnewapis.notificationsapi33

import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

@Composable
fun ShowNotificationUI(showButtonOnClick: () -> Unit) {
    val context = LocalContext.current
    var hasNotificationPermission by remember {
        if (Build.VERSION.SDK_INT >= 33) {
            mutableStateOf(
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            )
        } else {
            mutableStateOf(true)
        }
    }

    val permissionResultLanucher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission(), onResult = {
        isGranted ->
        hasNotificationPermission = isGranted
    })

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = {
            if (Build.VERSION.SDK_INT >= 33) {
                permissionResultLanucher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }) {
            Text(text = "Request Permission")
        }

        Button(onClick = {
            if(hasNotificationPermission) {
                showButtonOnClick
            }
        }) {
            Text(text = "Show Notification")
        }
    }
}
