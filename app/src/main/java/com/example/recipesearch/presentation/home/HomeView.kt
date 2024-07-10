package com.example.recipesearch.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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

class HomeView {
    @Composable
    fun SetupUI(navController: NavController, viewModel: HomeViewModel) {
//        viewModel.fetchData()
        Scaffold(
            bottomBar = {
                ButtomNav(viewModel)
            },
            content = { it ->
                if (viewModel.navScreen.value == NavItem.home) {
                    HomeScreen(
                        modifier = Modifier.padding(it), navController, viewModel
                    )
                } else {
                    FavouriteScreen(modifier = Modifier.padding(it), navController, viewModel)
                }
            }
        )
    }

    @Composable
    fun HomeScreen(modifier: Modifier, navController: NavController, viewModel: HomeViewModel) {
        val searchString = remember { mutableStateOf("") }
        LazyColumn(
            modifier = Modifier
                .padding(start = 15.dp, top = 10.dp)
        ) {
            item {
                Text(
                    "\uD83D\uDC4B Hey <user first name>",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    "Discover tasty and healthy receipt",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light
                )
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 15.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(242, 247, 253))
                        .clickable{
                            navController.navigate(Screen.SearchView.route)
                        }
                ) {
                    TextField(
                        value = searchString.value,
                        textStyle = TextStyle(color = Color.Black),
                        onValueChange = {
                            searchString.value = it
                        },
                        enabled = false,
                        leadingIcon = {
                            Icon(
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp)
                                    .padding(
                                        horizontal = 5.dp
                                    ),
                                painter = painterResource(id = R.drawable.serach_buton),
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
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    "Popular Recipes",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    content = {
                        if (viewModel.isDataLoaded.value) {
                            viewModel.recipes.value?.let { it1 ->
                                items(it1.number) { photo ->
                                    Box(
                                        modifier = Modifier
                                            .width(150.dp)
                                            .fillMaxHeight()
                                            .clip(RoundedCornerShape(10.dp)),
                                        contentAlignment = Alignment.BottomCenter
                                    ) {
                                        AsyncImage(
                                            modifier = Modifier
                                                .fillMaxSize(),
                                            model = it1.results[photo].image,
                                            contentDescription = "Shahi paneer",
                                            contentScale = ContentScale.Crop
                                        )
                                        Column(
                                            modifier = Modifier
                                                .height(60.dp)
                                                .fillMaxWidth()
                                                .background(
                                                    brush = Brush.verticalGradient(
                                                        colors = listOf(
                                                            Color(0, 0, 0, 0),
                                                            Color(10, 10, 10)
                                                        )
                                                    )
                                                ), verticalArrangement = Arrangement.Bottom
                                        ) {
                                            Text(
                                                modifier = Modifier
                                                    .width(120.dp)
                                                    .padding(start = 7.dp),
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis,
                                                text = it1.results[photo].title,
                                                color = Color.White,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(
                                                modifier = Modifier
                                                    .width(120.dp)
                                                    .padding(7.dp),
                                                maxLines = 1,
                                                overflow = TextOverflow.Ellipsis,
                                                text = "Ready in 25 min",
                                                color = Color.Gray,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Normal
                                            )

                                        }

                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                }
                            }
                        } else {
                            items(5) { photo ->
                                Box(
                                    modifier = Modifier
                                        .width(150.dp)
                                        .fillMaxHeight()
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(Color.Gray),
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                            }
                        }
                    },
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    "All recipes",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(600.dp),
                    content = {
                        if (viewModel.isDataLoaded.value) {
                            viewModel.recipes.value?.let { it1 ->
                                items(it1.number) { photo ->
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(100.dp)
                                            .padding(end = 15.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                            .border(
                                                width = 1.dp,
                                                color = Color.Gray,
                                                shape = RoundedCornerShape(10.dp)
                                            )
                                            .clickable {
                                                navController.navigate(
                                                    Screen.RecipeView.createRoute(
                                                        it1.results[photo].id
                                                    )
                                                )
                                            }
                                    ) {
                                        Row {
                                            AsyncImage(
                                                modifier = Modifier
                                                    .fillMaxHeight()
                                                    .width(100.dp),
                                                model = it1.results[photo].image,
                                                contentDescription = "",
                                                contentScale = ContentScale.Crop
                                            )
                                            Column(
                                                modifier = Modifier.fillMaxHeight(),
                                                verticalArrangement = Arrangement.Center
                                            ) {
                                                Text(
                                                    modifier = Modifier
                                                        .width(180.dp)
                                                        .padding(start = 7.dp),
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis,
                                                    text = it1.results[photo].title,
                                                    color = Color.Black,
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                                Text(
                                                    modifier = Modifier
                                                        .width(120.dp)
                                                        .padding(7.dp),
                                                    maxLines = 1,
                                                    overflow = TextOverflow.Ellipsis,
                                                    text = "Ready in 25 min",
                                                    color = Color.Gray,
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.Normal
                                                )

                                            }
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(10.dp))
                                }
                            }
                        } else {
                            items(2) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp)
                                        .padding(end = 15.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .clickable {
                                            navController.navigate(Screen.RecipeView.createRoute(1))
                                        }
                                        .background(Color.Gray)
                                )
                                Spacer(modifier = Modifier.height(10.dp))
                            }
                        }
                    }
                )
            }
        }
    }

    @Composable
    fun FavouriteScreen(
        modifier: Modifier,
        navController: NavController,
        viewModel: HomeViewModel
    ) {

        LazyColumn(
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = "Favourite recipes",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyColumn(
                        modifier = Modifier
                            .height(700.dp)
                    ) {
                        items(15) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color.Gray)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }

                }
            }
        }
    }

    @Composable
    fun ButtomNav(viewModel: HomeViewModel) {
        Row(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .width(180.dp)
                    .clickable {
                        viewModel.gotoHome()
                    }
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "home",
                    modifier = Modifier
                        .height(17.dp)
                        .width(17.dp),
                    tint = if (viewModel.navScreen.value == NavItem.home) Color.Red else Color.Black
                )
                Text(
                    text = "Home",
                    fontSize = 12.sp,
                    color = if (viewModel.navScreen.value == NavItem.home) Color.Red else Color.Black
                )
            }
            Column(
                modifier = Modifier
                    .width(180.dp)
                    .clickable {
                        viewModel.gotoFav()
                    }
                    .fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = if (viewModel.navScreen.value == NavItem.favourite) painterResource(id = R.drawable.fav_filled) else painterResource(
                        id = R.drawable.fav
                    ),
                    contentDescription = "home",
                    modifier = Modifier
                        .height(17.dp)
                        .width(17.dp),
                    tint = if (viewModel.navScreen.value == NavItem.favourite) Color.Red else Color.Black

                )
                Text(
                    text = "Favourite",
                    fontSize = 12.sp,
                    color = if (viewModel.navScreen.value == NavItem.favourite) Color.Red else Color.Black
                )
            }
        }
    }
}

enum class NavItem {
    home,
    favourite
}