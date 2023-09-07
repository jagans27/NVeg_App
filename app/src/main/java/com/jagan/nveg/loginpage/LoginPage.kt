package com.jagan.nveg.loginpage

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.jagan.nveg.R
import com.jagan.nveg.navigation.Screen
import com.jagan.nveg.ui.theme.DarkBlack

@Composable
fun LoginPage(
    navController: NavHostController
) {

    val isDark = isSystemInDarkTheme()

    Column(
        modifier = Modifier.background(if (isDark) DarkBlack else Color.White).fillMaxSize ()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .width(200.dp)
                .height(150.dp)
        ) {
            val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url("https://lottie.host/34acbbf5-b0d3-467c-8d74-734077faa47b/dX5bUdFbOE.json"))
            LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
        }

        Text(
            text = stringResource(R.string.terms),
            textAlign = TextAlign.Center,
            color = Color.Gray,
            modifier = Modifier.padding(start = 30.dp, end = 30.dp)
        )

        Spacer(Modifier.height(30.dp))

        Button(
            onClick = {
                navController.navigate(Screen.VerificationPage.route)
            },
            colors = ButtonDefaults.buttonColors(if (isDark) Color.White else DarkBlack)
        ) {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = null,
                tint = if (isDark) Color.Black else Color.White
            )
            Text(text = "    Log-in with e-mail", color = if (isDark) Color.Black else Color.White)
        }

        Spacer(Modifier.height(20.dp))

        Text(
            text = "Created and Maintained Nveg Team",
            color = Color.Gray,
            textAlign = TextAlign.End
        )

    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SampleViewOfLogin() {
    LoginPage(navController = rememberAnimatedNavController())
}