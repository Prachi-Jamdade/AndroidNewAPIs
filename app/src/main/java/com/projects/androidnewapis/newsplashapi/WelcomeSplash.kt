package com.projects.androidnewapis.newsplashapi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeSplash() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.DarkGray,
                shape = RectangleShape
            ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Welcome to New Splash API",
            color = Color.Magenta,
            fontSize = 20.sp
        )
    }
}