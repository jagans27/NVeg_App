package com.jagan.nveg.dashboard

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.jagan.nveg.R
import com.jagan.nveg.ui.theme.DarkBlack
import com.jagan.nveg.ui.theme.DarkLight
import com.jagan.nveg.ui.theme.LightWhite
import com.jagan.nveg.ui.theme.OrangeRed
import com.jagan.nveg.ui.theme.PurpleGrey40
import com.jagan.nveg.apputils.FishListItem
import kotlin.math.roundToInt


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ItemDashboard(navController: NavHostController) {
    val isDark = isSystemInDarkTheme()


    // fish data

    val fishItemList = listOf<FishListItem>(
        FishListItem("Mannanarak", 150, 14.5f),
        FishListItem("Boraial", 120, 132.5f),
        FishListItem("Carial", 140, 121.5f),
        FishListItem("Darial", 123, 50.5f),
        FishListItem("Zohral", 90, 12.0f),
    )

    val chickenValue = rememberSaveable { mutableStateOf(0f) }
    val muttonValue = rememberSaveable { mutableStateOf(0f) }
    val fishValue = rememberSaveable { mutableStateOf(listOf<FishListItem>()) }

    Column(
        modifier = Modifier
            .background(if (isDark) DarkBlack else Color.White)
            .verticalScroll(rememberScrollState())
            .padding(top = 10.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ItemCard { }
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
                R.drawable.chickenicon, "Chicken", 10f, 100, chickenValue
            )
            Divider()
            ItemCardDetails(
                R.drawable.muttonicon,
                "Mutton",
                15f,
                120,
                muttonValue
            )
            Divider()

            ItemCardDetailsForFish(R.drawable.fishicon, fishItemList, fishValue)

        }

        Button(
            onClick = {
                Log.d(
                    "DEBUG", "CHICKEN QUANTITY : ${chickenValue.value} \n" +
                            "CHICKEN QUANTITY : ${muttonValue.value} \n"
                )
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
    imageId: Int,
    item: String,
    quantity: Float,
    price: Int,
    sliderValueState: MutableState<Float>,
) {
    var sliderValue by sliderValueState

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
                painterResource(id = imageId), "icon",
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
                        text = "₹ ${price}/kg",
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                        color = OrangeRed
                    )
                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "Quantity : $quantity Kg",
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Italic,
                        color = if (isDark) Color.White else Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))

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
                        ).toFloat() - 0.5f else 0f
                    },
                    valueRange = 0f..quantity,
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
fun ItemCardDetailsForFish(
    imageId: Int,
    fishListItem: List<FishListItem>,
    fishListItemOrder: MutableState<List<FishListItem>>
) {


    val isDark = isSystemInDarkTheme()
    val context = LocalContext.current
    val sliderValue = rememberSaveable { mutableStateOf(0f) }
    val quantityOfFish = rememberSaveable { mutableStateOf(fishListItem[0].kg) }
    val priceOfFish = rememberSaveable { mutableStateOf(fishListItem[0].price) }
    val nameOfFish = rememberSaveable { mutableStateOf(fishListItem[0].name) }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painterResource(id = imageId), "icon",
                Modifier.size(70.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "Fish",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isDark) Color.White else Color.Black
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Add Fish to Your Order List",
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Italic,
                        color = if (isDark) Color.White else Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
        }

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                .fillMaxWidth()
                .background(if (isDark) Color(0xE4121212) else Color(0xFFFFF7F7)),
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(10.dp))
                Spacer(
                    modifier = Modifier
                        .height(5.dp)
                        .width(50.dp)
                        .clip(RoundedCornerShape(50.dp))
                        .background(Color.Gray)
                )
                Spacer(modifier = Modifier.height(10.dp))



                if (fishListItemOrder.value.isEmpty()) {
                    Text("- No fish items in the cart -", color = Color.Gray)
                } else {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("- Items in your cart -", color = Color.Gray)

                        Spacer(modifier = Modifier.height(10.dp))
                        val listSize = fishListItemOrder.value.size
                        for (i in 0 until listSize) {
                            val item = fishListItemOrder.value[i]
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 5.dp, end = 5.dp, top = 5.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(if (isDark) DarkLight else LightWhite),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Icon(
                                    painterResource(id = R.drawable.fishlisticon),
                                    null,
                                    Modifier
                                        .size(30.dp)
                                        .padding(start = 10.dp),
                                    tint = PurpleGrey40
                                )
                                Text(
                                    text = " ${item.name}",
                                    fontSize = 20.sp,
                                    color = if (isDark) Color.White else Color.Black,
                                    modifier = Modifier.fillMaxWidth(0.5f),
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 1
                                )

                                Text(
                                    text = "${item.kg} kg",
                                    color = Color.White,
                                    textAlign = TextAlign.Center,
                                    fontSize = 14.sp,
                                    modifier = Modifier
                                        .width(85.dp)
                                        .clip(RoundedCornerShape(100.dp))
                                        .background(Color(0xFF00521B))
                                )

                                IconButton(onClick = {
                                    val currentList = fishListItemOrder.value.toMutableList()
                                    currentList.removeAt(i)
                                    fishListItemOrder.value = currentList
                                }) {
                                    Icon(
                                        Icons.Default.Delete, null, tint = Color.Red
                                    )
                                }

                            }
                        }

                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = if (isDark) DarkLight else LightWhite
                ),
                shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    DropDownFishItem(fishListItem, sliderValue, priceOfFish, nameOfFish,quantityOfFish)

                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Slider(
                                value = sliderValue.value,
                                onValueChange = {
                                    sliderValue.value = if (it > 0.1) String.format(
                                        "%.1f",
                                        (((it + 0.25) / 0.5).roundToInt() * 0.5)
                                    ).toFloat() - 0.5f else 0f
                                },
                                valueRange = 0f..quantityOfFish.value,
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
                            value = sliderValue.value.toString(),
                            onValueChange = {
                            },
                            readOnly = true,
                            label = {
                                Text(text = "fish Kg", fontSize = 10.sp)
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

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(onClick = {
                        if (sliderValue.value > 0f) {
                            val currentList = fishListItemOrder.value.toMutableList()
                            val itemDetail =
                                FishListItem(nameOfFish.value, priceOfFish.value, sliderValue.value)
                            val index =
                                currentList.indexOfFirst { it.name == itemDetail.name && it.price == itemDetail.price }

                            if (index != -1) currentList.removeAt(index)

                            currentList.add(itemDetail)
                            //fishListItemOrder.value = currentList.sortedBy { it.name }
                            fishListItemOrder.value = currentList

                        } else {
                            Toast.makeText(context, "Choose the Quantity", Toast.LENGTH_SHORT)
                                .show()
                        }

                    }, modifier = Modifier.fillMaxWidth(0.7f)) {
                        Text(text = "Add Fish To Card ")
                        Icon(Icons.Default.AddCircle, null)

                    }
                }
            }
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownFishItem(
    fishListItem: List<FishListItem>,
    sliderValue: MutableState<Float>,
    priceOfFish: MutableState<Int>,
    nameOfFish: MutableState<String>,
    quantityOfFish: MutableState<Float>
) {

    val isDark = isSystemInDarkTheme()
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(fishListItem[0].name) }
    var selectedFishPrice by remember { mutableStateOf(fishListItem[0].price) }
    var selectedFishQuantity by remember { mutableStateOf(fishListItem[0].kg) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth(0.7f),
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
                for (i in fishListItem.indices) {
                    DropdownMenuItem(
                        text = {
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    modifier = Modifier.width(50.dp),
                                    text = fishListItem[i].name,
                                    color = if (isDark) Color.White else Color.Black,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Text(
                                    modifier = Modifier
                                        .width(70.dp)
                                        .clip(
                                            RoundedCornerShape(100.dp)
                                        )
                                        .background(Color(0xFF00521B)),
                                    text = "₹-${fishListItem[i].price}/kg",
                                    textAlign = TextAlign.Center,
                                    color = if (isDark) Color.White else Color.Black
                                )

                                Text(
                                    modifier = Modifier
                                        .width(60.dp)
                                        .clip(
                                            RoundedCornerShape(100.dp)
                                        )
                                        .background(Color(0xFFFFB038)),
                                    text = "${fishListItem[i].kg} Kg",
                                    textAlign = TextAlign.Center,
                                    color = if (isDark) Color.White else Color.Black
                                )

                            }
                        },
                        onClick = {
                            selectedText = fishListItem[i].name
                            selectedFishPrice = fishListItem[i].price
                            selectedFishQuantity = fishListItem[i].kg

                            sliderValue.value = 0f
                            nameOfFish.value = fishListItem[i].name
                            priceOfFish.value = fishListItem[i].price
                            quantityOfFish.value = fishListItem[i].kg

                            expanded = false
                        }
                    )
                    if (i != fishListItem.size - 1)
                        Divider()
                }
            }
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column(
            modifier = Modifier.fillMaxWidth(1f),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "${selectedFishPrice}/kg",
                    color = OrangeRed,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    painterResource(id = R.drawable.rupeeicon),
                    null,
                    Modifier.size(20.dp),
                    tint = OrangeRed
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "$selectedFishQuantity kg",
                    color = if (isDark) Color.White else Color.Black,
                    fontSize = 15.sp
                )
                Icon(
                    painterResource(id = R.drawable.kgicon),
                    null,
                    Modifier.size(20.dp),
                    tint = if (isDark) Color.White else Color.Black
                )
            }
        }


    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun ItemDashboardLightPreview() {
    ItemDashboard(rememberAnimatedNavController())
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ItemDashboardDarkPreview() {
    ItemDashboard(rememberAnimatedNavController())
}
