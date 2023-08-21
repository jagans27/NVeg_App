package com.jagan.nveg.loginpage

import android.content.res.Configuration
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jagan.nveg.R
import com.jagan.nveg.ui.theme.DarkBlack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationPage() {
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
                .width(300.dp)
                .height(200.dp)
        ) {
            val composition by rememberLottieComposition(spec = LottieCompositionSpec.Url("https://lottie.host/7b1b4905-aed1-433d-81dd-1d48afa905ba/GTUiSb34bs.json"))
            LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever)
        }

        var phno by rememberSaveable { mutableStateOf("") }
        var otp by rememberSaveable { mutableStateOf("") }
        var isOtpEnabled by rememberSaveable { mutableStateOf(false) }

        Text(
            text = "OTP Verification",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            color = if (isDark) Color.White else DarkBlack
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(R.string.otp_verification),
            fontSize = 15.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 10.dp, bottom = 20.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = phno,
            onValueChange = {
                if (it.length <= 10)
                    phno = it
            },
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = if (isDark) Color.Black else Color.White,
                containerColor = if (isDark) Color.White else DarkBlack,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            label = { Text("phone no.", color = if (isDark) Color.Black else Color.White) },
            textStyle = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                letterSpacing = 2.sp
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.clip(RoundedCornerShape(100.dp)),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "",
                    tint = if (isDark) Color.Black else Color.White
                )
            }
        )

        if (isOtpEnabled) {
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = otp,
                onValueChange = {
                    if (it.length <= 6)
                        otp = it
                },
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = if (isDark) Color.Black else Color.White,
                    containerColor = if (isDark) Color.White else DarkBlack,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    letterSpacing = 2.sp
                ),
                label = {
                    Text(
                        "enter 6 digit otp",
                        color = if (isDark) Color.Black else Color.White
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.clip(RoundedCornerShape(100.dp)),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "",
                        tint = if (isDark) Color.Black else Color.White
                    )
                }
            )
        }

        Spacer(Modifier.height(30.dp))

        Button(
            onClick = {
                isOtpEnabled = true
            },
            colors = ButtonDefaults.buttonColors(if (isDark) Color.White else Color.Black)
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = null,
                tint = if (isDark) Color.Black else Color.White
            )
            Text(text = "  Generate OTP", color = if (isDark) Color.Black else Color.White)
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewVerificationPage() {
    VerificationPage()
}