package com.jagan.nveg.dashboard

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jagan.nveg.R
import com.jagan.nveg.ui.theme.DarkBlack
import com.jagan.nveg.ui.theme.DarkBlackLight
import com.jagan.nveg.ui.theme.LightWhite
import com.jagan.nveg.ui.theme.OrangeRed


@Composable
fun UserDashboard() {

    val isDark = isSystemInDarkTheme()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(if (isDark) DarkBlack else Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            colors = CardDefaults.cardColors(if (isDark) DarkBlackLight else Color.White)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profilepic),
                    contentDescription = "profile pic",
                    modifier = Modifier
                        .clip(RoundedCornerShape(100))
                        .size(120.dp)
                )
                Column(
                    modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Ramesh KL",
                        fontSize = 25.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = if (isDark) LightWhite else Color.Black
                    )
                    CardText(txt = "+91-9898989800")
                    CardText(txt = "ramesh143@gmail.com")
                    CardText(txt = "1/119A, School St., Echoor, Chennai-603105")

                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, end = 20.dp, start = 20.dp),
                        colors = ButtonDefaults.buttonColors( OrangeRed)
                    )
                    {

                        Icon(
                            Icons.Default.AccountBox,
                            "update",
                            modifier = Modifier
                                .padding(end = 10.dp)

                        )
                        Text("Update ", fontSize = 14.sp)

                    }
                }

            }
        }
        CardButtonFun(txt = " Settings",Icons.Default.Settings) {}
        CardButtonFun(txt = " My Order",Icons.Default.ShoppingCart) {}
        CardButtonFun(txt = " Feedback",Icons.Default.Favorite) {}
        CardButtonFun(txt = " Share",Icons.Default.Share) {}
        ElevatedButton(
            onClick = {}, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, end = 20.dp, start = 20.dp)
                .height(45.dp),
            colors = ButtonDefaults.buttonColors( Color.Red),
            elevation = ButtonDefaults.elevatedButtonElevation(10.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(Icons.Default.Delete,"")
            Text(text = " Delete Account", fontSize = 18.sp, color = Color.White)
        }

    }
}


@Composable
fun CardButtonFun(
    txt: String,
    icon : ImageVector,
    onClick: () -> Unit) {
    val isDark = isSystemInDarkTheme()
    ElevatedButton(
        onClick = onClick, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, end = 20.dp, start = 20.dp)
            .height(45.dp),
        colors = ButtonDefaults.buttonColors( if (isDark) DarkBlackLight else Color.White),
        elevation = ButtonDefaults.elevatedButtonElevation(10.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Icon(icon,txt, tint = OrangeRed)
        Text(text = txt, fontSize = 18.sp, color = OrangeRed)
    }
}


@Composable
fun CardText(txt: String) {
    val isDark = isSystemInDarkTheme()
    Text(
        modifier = Modifier.padding(top = 5.dp),
        text = txt,
        fontSize = 18.sp,
        fontFamily = FontFamily.SansSerif,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = if (isDark) LightWhite else Color.Black
    )
}


@Preview
@Composable
fun UserDashboardPreview() {
    UserDashboard()
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun UserDashboardPreview1() {
    UserDashboard()
}