package com.jagan.nveg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.jagan.nveg.loginpage.UserDetailsPage
import com.jagan.nveg.navigation.NavGraph
import com.jagan.nveg.navigation.Screen

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //splashScreen
        installSplashScreen()

        setContent {
            Surface(
                modifier = Modifier
                    .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
                .fillMaxSize(),
                color = if (isSystemInDarkTheme()) Color.Black else Color.White
            ) {
                NavGraph(navController = rememberAnimatedNavController(), Screen.UserDetailsPage.route)
            }
        }

    }
}
