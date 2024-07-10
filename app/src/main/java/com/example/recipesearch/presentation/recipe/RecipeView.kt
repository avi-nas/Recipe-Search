package com.example.recipesearch.presentation.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.recipesearch.R


class RecipeView {
    @Composable
    fun SetupUI(navController: NavController, viewModel: RecipeViewModel, id: Int) {
        viewModel.fetchData(id)
        RecipeScreen(viewModel)
    }

    @Composable
    fun RecipeScreen(viewModel: RecipeViewModel) {
        val scrollState = rememberScrollState()
        if (viewModel.isDataLoaded.value) {
            viewModel.recipe.value?.let { recipe ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    Box(
                        modifier = Modifier
                            .height(360.dp)
                            .fillMaxWidth()
                    ) {
                        AsyncImage(
                            model = recipe.image,
                            modifier = Modifier
                                .height(360.dp)
                                .fillMaxWidth(),
                            contentDescription = "",
                            contentScale = ContentScale.Crop
                        )
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(15.dp),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Box(
                                    modifier = Modifier
                                        .height(40.dp)
                                        .width(40.dp)
                                        .clip(RoundedCornerShape(size = 24.dp))
                                        .background(Color.White),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.fav),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .height(24.dp)
                                            .width(24.dp)
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                Color(0, 0, 0, 0),
                                                Color(10, 10, 10)
                                            )
                                        )
                                    )
                            ) {
                                Text(
                                    text = recipe.title,
                                    color = Color.White,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 24.sp,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }

                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Column(
                        modifier = Modifier
                            .padding(15.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
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
                                        text = "${recipe.cookingMinutes} min",
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
                                        text = "${recipe.servings}",
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
                                        text = "${recipe.pricePerServing}",
                                        fontSize = 16.sp,
                                        color = Color.Red,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Text(text = "Ingredients", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(5.dp))
                        LazyRow {
                            items(recipe.extendedIngredients.size) { ingredient ->
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    AsyncImage(
                                        modifier = Modifier
                                            .height(86.dp)
                                            .width(86.dp)
                                            .clip(RoundedCornerShape(86.dp)),
                                        model = recipe.extendedIngredients[ingredient].image,
                                        contentDescription = "",
                                        contentScale = ContentScale.Crop
                                    )
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Text(
                                        recipe.extendedIngredients[ingredient].name,
                                        fontSize = 12.sp
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Instructions", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = recipe.instructions)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Equipments", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(5.dp))
                        LazyRow {
                            items(recipe.extendedIngredients.size) { ingredient ->
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    AsyncImage(
                                        modifier = Modifier
                                            .height(86.dp)
                                            .width(86.dp)
                                            .clip(RoundedCornerShape(86.dp)),
                                        model = recipe.extendedIngredients[ingredient].image,
                                        contentDescription = "",
                                        contentScale = ContentScale.Crop
                                    )
                                    Spacer(modifier = Modifier.height(5.dp))
                                    Text(
                                        "Onion",
                                        fontSize = 12.sp
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Quick Summary", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = recipe.summary)
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        } else {
            Text(text = "Loading")
        }
    }

}