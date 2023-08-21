package com.jagan.nveg.detailspage

import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jagan.nveg.ui.theme.DarkBlack
import com.jagan.nveg.ui.theme.OrangeRed

@Composable
fun AboutPage() {

    val isDark = isSystemInDarkTheme()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(if (isDark) DarkBlack else Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .width(200.dp)
                .height(150.dp)
        ) {
            val composition by rememberLottieComposition(
                spec = LottieCompositionSpec.Url("https://lottie.host/6b9c8ffb-fcbd-49ef-95b5-642699352aee/k0CbT46Egt.json")
            )
            LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
        }

        Text(
            text = "About us",
            color = if (isDark) Color.White else Color.Black,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(20.dp))

        Text(
            modifier = Modifier.padding(30.dp, 0.dp),
            text = "\"Welcome to our app, offering premium, locally sourced fresh non-vegetarian products including fish, chicken, mutton, and more.\"",
            color = if (isDark) Color.White else Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )

        Spacer(Modifier.height(20.dp))


        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = OrangeRed
                    )
                ) {
                    append("Designed and Created by ")
                }

                append("Jagan")

            },
            color = if (isDark) Color.White else Color.Black,
            fontSize = 16.sp
        )


        Spacer(Modifier.height(20.dp))

        Text(
            text = "Since @2K23", color = Color.Gray, textAlign = TextAlign.End
        )

    }

}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun AboutPagePreview() {
    AboutPage()
}

@Preview
@Composable
fun AboutPagePreview1() {
    AboutPage()
}