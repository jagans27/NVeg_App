package com.jagan.nveg.dashboard

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jagan.nveg.R
import com.jagan.nveg.ui.theme.DarkBlack
import com.jagan.nveg.ui.theme.DarkBlackLight
import com.jagan.nveg.ui.theme.DarkGreen
import com.jagan.nveg.ui.theme.DarkLight
import com.jagan.nveg.ui.theme.LightWhite
import com.jagan.nveg.ui.theme.OrangeRed
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPage() {
    val isDark = isSystemInDarkTheme()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var search by rememberSaveable { mutableStateOf("") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = if (isDark) DarkBlackLight else Color.White,
                modifier = Modifier.padding(end = 40.dp)
            ) {

                AppDrawerHeader()

                Spacer(modifier = Modifier.height(20.dp))

                NavigationDrawerItemClick("Home", Icons.Default.Home, true) {}
                NavigationDrawerItemClick("About", Icons.Default.Info, false) {}
                NavigationDrawerItemClick("Settings", Icons.Default.Settings, false) {}
                NavigationDrawerItemClick("Log out", Icons.Default.Warning, false) {}

            }
        },
        scrimColor = Color.Transparent
    ) {
        Scaffold(
            topBar = {
                AppBar {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(if (isDark) DarkBlack else Color.White)
                    .padding(top = 38.dp),
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                TextField(
                    value = search,
                    onValueChange = {
                        search = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .background(Color.Transparent)
                        .clip(RoundedCornerShape(50.dp)),
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = if (!isDark) DarkLight else LightWhite,
                        containerColor = if (!isDark) LightWhite else DarkLight,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            "Search",
                            tint = if (!isDark) DarkLight else LightWhite
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Search by shop name | area",
                            color = if (!isDark) DarkLight else LightWhite
                        )
                    }
                )
                LazyColumn {
                    items(40) {
                        ItemCard()
                    }
                }
            }
        }
    }
}

@Composable
fun ItemCard() {
    val isDark = isSystemInDarkTheme()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSystemInDarkTheme()) DarkLight else LightWhite)
            .height(130.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.samplecardimage),
            "shop image",
            modifier = Modifier
                .size(130.dp)
                .clip(RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp))
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Chicken Street",
                    color = if (isDark) Color.White else Color.Black,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    Icons.Default.CheckCircle,
                    "",
                    tint = DarkGreen,
                    modifier = Modifier.size(16.dp)
                )
            }

            Text(
                text = "Chennai - 603105",
                color = if (isDark) Color.White else Color.Black,
                fontSize = 15.sp,
                fontStyle = FontStyle.Italic
            )

            Text(
                text = "Status : Deliverable",
                color = if (isDark) Color.White else Color.Black,
                fontSize = 17.sp
            )

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "CMBF", color = if (isDark) Color.White else Color.Black, fontSize = 17.sp)
                Spacer(Modifier.width(10.dp))
                Text(text = "4.3", color = OrangeRed, fontWeight = FontWeight.Bold)
                Icon(
                    Icons.Default.Star,
                    "ratings",
                    tint = OrangeRed,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ListPagePreview() {
    ListPage()
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ListPagePreview1() {
    ListPage()
}