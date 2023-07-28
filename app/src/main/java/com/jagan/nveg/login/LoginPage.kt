package com.jagan.nveg.login

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
import androidx.compose.material.icons.filled.Call
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
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jagan.nveg.R
import com.jagan.nveg.ui.theme.DarkBlack

@Composable
fun Login() {

    val isDark = isSystemInDarkTheme()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(if(isDark) DarkBlack else Color.White ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            Modifier
                .width(200.dp)
                .height(150.dp)) {
            val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url("https://lottie.host/34acbbf5-b0d3-467c-8d74-734077faa47b/dX5bUdFbOE.json"))
            LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
        }

        Text(text = stringResource(R.string.terms), textAlign = TextAlign.Center,color = Color.Gray, modifier = Modifier.padding(start = 30.dp, end = 30.dp))

        Spacer(Modifier.height(30.dp))

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(if(isDark) Color.White else DarkBlack)
        ) {
            Icon(imageVector = Icons.Default.Call, contentDescription = null, tint = if(isDark) Color.Black else Color.White)
            Text(text = "    Log-in with Ph. no.", color = if(isDark) Color.Black else Color.White )
        }

        Spacer(Modifier.height(20.dp))

        Text(text = "Created and Maintained Nveg Team",color= Color.Gray, textAlign = TextAlign.End)

    }

}

@Preview
@Composable
fun SampleViewOfLogin() {
    Login()
}