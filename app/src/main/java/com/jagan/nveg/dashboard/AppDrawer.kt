package com.jagan.nveg.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jagan.nveg.R
import com.jagan.nveg.ui.theme.DarkBlack
import com.jagan.nveg.ui.theme.LightWhite
import com.jagan.nveg.ui.theme.OrangeRedLight

@Composable
fun AppDrawerHeader() {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .size(200.dp)
            .clip(RoundedCornerShape(bottomEndPercent = 20, bottomStartPercent = 20))
            .background(Color( 0xFF161616 ))
    ) {
        Image(
            painter = painterResource(id = R.drawable.nvegapplogo),
            contentDescription = "app logo",
            modifier = Modifier.size(150.dp),
        )
        Text(
            "NVeg - Do as you like",
            fontFamily = FontFamily.SansSerif,
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(Modifier.height(30.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerItemClick(
    label: String,
    icons: ImageVector,
    selected: Boolean,
    onClickAction: () -> Unit
) {

    NavigationDrawerItem(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp),
        colors = NavigationDrawerItemDefaults.colors(
            unselectedContainerColor =  LightWhite ,
            selectedContainerColor = OrangeRedLight
        ),
        icon = { Icon(icons, label, tint = DarkBlack) },
        label = {
            Text(
                text = label,
                fontSize = 16.sp,
                color = DarkBlack
            )
        },
        selected = selected,
        onClick = onClickAction
    )


}


