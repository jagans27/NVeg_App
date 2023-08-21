package com.jagan.nveg.dashboard

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jagan.nveg.R
import com.jagan.nveg.ui.theme.DarkBlack
import com.jagan.nveg.ui.theme.DarkLight
import com.jagan.nveg.ui.theme.LightWhite
import com.jagan.nveg.ui.theme.OrangeRed
import kotlin.math.roundToInt


@Composable
fun ItemDashboard() {
    val isDark = isSystemInDarkTheme()



    // fish data
    val fishItems = "AB,CD,EF,GH,IJ"
    val fishItemCost = "12,34,54,67,89"
    val fishItemQuantity = "30.5,34.5,65.0,12.5,89.5"

    val chickenValue = rememberSaveable { mutableStateOf(0f) }
    val muttonValue = rememberSaveable { mutableStateOf(0f) }
    val fishValue = rememberSaveable { mutableStateOf(0f) }
    val fishType = rememberSaveable { mutableStateOf(fishItems.substring(0,fishItems.indexOf(","))) }

    Column(
        modifier = Modifier
            .background(if (isDark) DarkBlack else Color.White)
            .verticalScroll(rememberScrollState())
            .padding(top = 10.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ItemCard()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(if (isDark) DarkLight else LightWhite),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ItemCardDetails(
                R.drawable.chickenicon, "Chicken", 10f, 100, chickenValue,fishType,
                emptyList(), emptyList(), emptyList()
            )
            Divider()
            ItemCardDetails(
                R.drawable.muttonicon,
                "Mutton",
                15f,
                120,
                muttonValue,
                fishType,
                emptyList(),
                emptyList(),
                emptyList()
            )
            Divider()
            ItemCardDetails(
                R.drawable.fishicon,
                "Fish",
                0f,
                0,
                fishValue,
                fishType,
                fishItems.split(","),
                fishItemCost.split(","),
                fishItemQuantity.split(",")
            )
        }

        Button(
            onClick = {
                Log.d("DEBUG", "CHICKEN QUANTITY : ${chickenValue.value} \n" +
                        "CHICKEN QUANTITY : ${muttonValue.value} \n" +
                        "FISH QUANTITY : ${fishValue.value} - ${fishType.value}")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
        ) {
            Icon(Icons.Default.ShoppingCart, null)
            Text(" Place Order")
        }

        Text(
            text = "✔ Note : Feel free to leave the quantity as '0' \nif you choose not to buy the item",
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemCardDetails(
    imageDd: Int,
    item: String,
    quantity: Float,
    price: Int,
    sliderValueState: MutableState<Float>,
    fishItemValue: MutableState<String>,
    fishList: List<String>,
    fishListPrice: List<String>,
    fishListQuantity: List<String>
) {

    val itemQuantity = rememberSaveable {
        mutableStateOf(if (item == "Fish") fishListQuantity[0].toFloat() else quantity)
    }
    val itemPrice = rememberSaveable {
        mutableStateOf(if (item == "Fish") fishListPrice[0].toInt() else price)
    }

    var sliderValue by sliderValueState

    LaunchedEffect(key1 = itemQuantity , block = {
        if(itemQuantity.value<=sliderValue)
            sliderValue = itemQuantity.value
    })

    val isDark = isSystemInDarkTheme()

    Column(
        modifier = Modifier
            .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painterResource(id = imageDd), "icon",
                Modifier.size(70.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = item,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isDark) Color.White else Color.Black
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "₹ ${itemPrice.value}/kg",
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        color = OrangeRed
                    )
                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Quantity : ${itemQuantity.value} Kg",
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Italic,
                        color = if (isDark) Color.White else Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
        }

        if (item == "Fish") {
            Spacer(Modifier.height(5.dp))
            DropDownFishItem(
                fishList,
                fishListPrice,
                fishListQuantity,
                itemPrice,
                itemQuantity,
                fishItemValue
            )
            Spacer(Modifier.height(5.dp))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Slider(
                    value = sliderValue,
                    onValueChange = {
                        sliderValue = if (it > 0.1) String.format(
                            "%.1f",
                            (((it + 0.25) / 0.5).roundToInt() * 0.5)
                        ).toFloat()-0.5f else 0f
                    },
                    valueRange = 0f..itemQuantity.value,
                    modifier = Modifier
                        .width(200.dp)
                        .height(25.dp),
                    colors = SliderDefaults.colors(
                        inactiveTickColor = if (isDark) LightWhite else DarkLight,
                        activeTrackColor = if (isDark) Color.DarkGray else DarkLight,
                        inactiveTrackColor = if (isDark) LightWhite else Color.Gray
                    )
                )

                Text(
                    "Slide to select weight(kg)",
                    color = if (isDark) Color.White else Color.Black
                )
            }

            Spacer(modifier = Modifier.width(5.dp))

            OutlinedTextField(
                value = sliderValue.toString(),
                onValueChange = {
                },
                readOnly = true,
                label = {
                    Text(text = "$item Kg", fontSize = 10.sp)
                },
                modifier = Modifier
                    .width(99.dp)
                    .height(60.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = if (isDark) Color.White else Color.Black,
                    focusedLabelColor = if (isDark) Color.White else Color.Black,
                    unfocusedBorderColor = if (isDark) Color.White else Color.Black,
                    unfocusedLabelColor = if (isDark) Color.White else Color.Black,
                    textColor = if (isDark) Color.White else Color.Black,
                ),
                textStyle = TextStyle.Default.copy(fontSize = 15.sp),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownFishItem(
    fishItem: List<String>,
    fishItemPrice: List<String>,
    fishItemQuantity: List<String>,
    priceOfFishState: MutableState<Int>,
    quantityOfFishState: MutableState<Float>,
    fishNameState: MutableState<String>,
) {

    var fishName by fishNameState
    var priceOfFish by priceOfFishState
    var quantityOfFish by quantityOfFishState

    val isDark = isSystemInDarkTheme()
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(fishItem[0]) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        contentAlignment = Alignment.Center
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            OutlinedTextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text("select fish item")
                },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .height(60.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = if (isDark) Color.White else Color.Black,
                    focusedLabelColor = if (isDark) Color.White else Color.Black,
                    unfocusedBorderColor = if (isDark) Color.White else Color.Black,
                    unfocusedLabelColor = if (isDark) Color.White else Color.Black,
                    textColor = if (isDark) Color.White else Color.Black,
                ),
                textStyle = TextStyle.Default.copy(fontSize = 15.sp),

                )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                Modifier.background(if (isDark) DarkBlack else Color.White)
            ) {
                for (i in fishItem.indices) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = fishItem[i],
                                color = if (isDark) Color.White else Color.Black
                            )
                        },
                        onClick = {
                            selectedText = fishItem[i]
                            fishName = selectedText
                            priceOfFish = fishItemPrice[i].toInt()
                            quantityOfFish = fishItemQuantity[i].toFloat()
                            expanded = false
                            Toast.makeText(context, fishItem[i], Toast.LENGTH_SHORT).show()
                        }
                    )
                    if (i != fishItem.size - 1)
                        Divider()
                }
            }
        }
    }
}

@Preview
@Composable
fun ItemDashboardLightPreview() {
    ItemDashboard()
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ItemDashboardDarkPreview() {
    ItemDashboard()
}
