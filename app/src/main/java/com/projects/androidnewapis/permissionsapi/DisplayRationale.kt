package com.projects.androidnewapis.permissionsapi

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun DisplayRationale() {
    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA
        )
    )

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner, effect = {
        val obeserver = LifecycleEventObserver {
                _,
                event ->
            if(event == Lifecycle.Event.ON_START) {
                permissionsState.launchMultiplePermissionRequest()
            }
        }

        lifecycleOwner.lifecycle.addObserver(obeserver)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(obeserver)
        }
    })

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        permissionsState.permissions.forEach {
                permission ->
            when(permission.permission) {
                Manifest.permission.CAMERA -> {
                    when {
                        permission.hasPermission -> {
                            Text(text = "Camera Permission Accepted")
                        }
                        permission.shouldShowRationale -> {
                            Text(text = "Camera Permission is needed to access the camera")
                        }
                        permission.isPermanentlyDenied() -> {
                            Text(text = "Camera Permission was permanently denied. You can enable it in the App Settings.")
                        }
                    }
                }
                Manifest.permission.RECORD_AUDIO -> {
                    when {
                        permission.hasPermission -> {
                            Text(text = "Audio Permission Accepted")
                        }
                        permission.shouldShowRationale -> {
                            Text(text = "Audio Permission is needed to access the camera")
                        }
                        permission.isPermanentlyDenied() -> {
                            Text(text = "Audio Permission was permanently denied. You can enable it in the App Settings.")
                        }
                    }
                }
            }
        }
    }

}