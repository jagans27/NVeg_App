package com.jagan.nveg.dashboard


import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jagan.nveg.R
import com.jagan.nveg.ui.theme.DarkBlackLight
import com.jagan.nveg.ui.theme.LightWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    navigationIconOnClick: () -> Unit
) {
    val isDark = isSystemInDarkTheme()

    TopAppBar(
        title = {
            Text(text = "NVeg")
        },
        navigationIcon = {
            IconButton(onClick = navigationIconOnClick) {
                Icon(Icons.Filled.Menu, "Menu")
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = if (isDark) DarkBlackLight else LightWhite,
            scrolledContainerColor = Color.Red,
            navigationIconContentColor = if (!isDark) DarkBlackLight else LightWhite,
            titleContentColor = if (!isDark) DarkBlackLight else LightWhite,
            actionIconContentColor = if (!isDark) DarkBlackLight else LightWhite,
        ),
        actions = {

            BadgedBox(badge = { Badge(modifier = Modifier.padding(top = 20.dp)) { Text("8", fontSize = 10.sp) } }) {
                Icon(
                    Icons.Default.Notifications, "",
                    Modifier.size(27.dp)
                )
            }

            Spacer(Modifier.width(20.dp))

            Image(
                painter = painterResource(id = R.drawable.profilepic),
                "",
                modifier = Modifier
                    .padding(end = 20.dp)
                    .size(35.dp)
                    .clip(RoundedCornerShape(100.dp))
            )
        }
    )
}