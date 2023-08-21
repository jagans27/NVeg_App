package com.jagan.nveg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.jagan.nveg.dashboard.ItemDashboard
import com.jagan.nveg.detailspage.AboutPage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //splashScreen
        installSplashScreen()

        setContent {
            ItemDashboard()
        }

    }
}
