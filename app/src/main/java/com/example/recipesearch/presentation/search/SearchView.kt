package com.example.recipesearch.presentation.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.recipesearch.R
import com.example.recipesearch.Screen
import com.example.recipesearch.presentation.home.HomeViewModel
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class SearchView {
    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun SetupUI(navController: NavController, viewModel: SearchViewModel) {
        val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

        ModalBottomSheetLayout(
            sheetState = sheetState,
            sheetContent = {
                ButtomSheet(sheetState)
            }
        ) {
            Scaffold(
                content = {
                    SearchScreen(
                        modifier = Modifier.padding(it),
                        navController = navController,
                        viewModel = viewModel,
                        sheetState
                    )
                }
            )
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun SearchScreen(
        modifier: Modifier,
        navController: NavController,
        viewModel: SearchViewModel,
        sheetState: ModalBottomSheetState
    ) {
        val searchString = remember { mutableStateOf("") }
        val scope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(242, 247, 253))
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(20.dp)
                    ),
                value = searchString.value,
                textStyle = TextStyle(color = Color.Black),
                onValueChange = {
                    searchString.value = it
                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .padding(
                                horizontal = 5.dp
                            )
                            .clickable {
                                navController.popBackStack()
                            },
                        painter = painterResource(
                            id = R.drawable.back_arrow
                        ),
                        contentDescription = "search"
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                placeholder = {
                    Text(text = "Search any recipe")
                }
            )
            LazyColumn {
                items(3) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clickable {
                                scope.launch {
                                    sheetState.show()
                                }
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .height(20.dp)
                                .width(20.dp),
                            painter = painterResource(id = R.drawable.ic_food),
                            contentDescription = "food"
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Khadai paneer ",
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun ButtomSheet(sheetState: ModalBottomSheetState) {
        val scope = rememberCoroutineScope()

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .height(24.dp)
                            .width(24.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(Color(242, 247, 253))
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(24.dp)
                            )
                            .clickable {
                                scope.launch {
                                    sheetState.hide()
                                }
                            },
                        painter = painterResource(id = R.drawable.back_arrow),
                        contentDescription = ""
                    )
                    Text(
                        text = "Shahi Paneer",
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                Icon(
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp),
                    painter = painterResource(id = R.drawable.fav),
                    contentDescription = ""
                )
            }
            Image(
                painter = painterResource(id = R.drawable.shahi_paneer),
                modifier = Modifier
                    .height(360.dp)
                    .fillMaxWidth(),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Box(
                    modifier = Modifier
                        .height(56.dp)
                        .width(104.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .border(
                            width = 1.dp,
                            color = Color.Gray.copy(alpha = 0.2F),
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Ready in",
                            fontSize = 12.sp
                        )
                        Text(
                            text = "45 min",
                            fontSize = 16.sp,
                            color = Color.Red,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .height(56.dp)
                        .width(104.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .border(
                            width = 1.dp,
                            color = Color.Gray.copy(alpha = 0.2F),
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Servings",
                            fontSize = 12.sp
                        )
                        Text(
                            text = "6",
                            fontSize = 16.sp,
                            color = Color.Red,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .height(56.dp)
                        .width(104.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .border(
                            width = 1.dp,
                            color = Color.Gray.copy(alpha = 0.2F),
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Price/serving",
                            fontSize = 12.sp
                        )
                        Text(
                            text = "324",
                            fontSize = 16.sp,
                            color = Color.Red,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(5.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(15.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Red),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Get ingredients",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }

}