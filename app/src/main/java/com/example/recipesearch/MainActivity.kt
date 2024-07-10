package com.example.recipesearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.android.volley.toolbox.ImageRequest
import com.example.recipesearch.presentation.welcome.WelcomeView
import com.example.recipesearch.presentation.welcome.WelcomeViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.recipesearch.presentation.home.HomeView
import com.example.recipesearch.presentation.home.HomeViewModel
import com.example.recipesearch.presentation.recipe.RecipeView
import com.example.recipesearch.presentation.recipe.RecipeViewModel
import com.example.recipesearch.presentation.search.SearchView
import com.example.recipesearch.presentation.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.WelcomeView.route) {
        composable(Screen.FirstScreen.route) {
            FirstScreen(navController = navController)
        }
        composable(Screen.WelcomeView.route) {
            val viewModel: WelcomeViewModel = hiltViewModel()
            WelcomeView(navController = navController, viewModel)
        }
        composable(Screen.HomeView.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeView().SetupUI(navController = navController, viewModel)
        }
        composable(Screen.SearchView.route) {
            val viewModel: SearchViewModel = hiltViewModel()
            SearchView().SetupUI(navController = navController, viewModel)
        }
        composable(
            route = Screen.RecipeView.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            val viewModel: RecipeViewModel = hiltViewModel()
            id?.let {
                RecipeView().SetupUI(navController = navController, viewModel, it)
            }
        }
    }
}

sealed class Screen(val route: String) {
    object FirstScreen : Screen("first_screen")
    object WelcomeView : Screen("WelcomeView")
    object HomeView : Screen("HomeView")
    object SearchView : Screen("SearchView")
    object RecipeView : Screen("RecipeView/{id}") {
        fun createRoute(id: Int) = "RecipeView/$id"
    }}




@Composable
fun FirstScreen(navController : NavController) {
    Scaffold(
        topBar = {

        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "This is the first screen")
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    navController.navigate(Screen.WelcomeView.route)
                }) {
                    Text("Go to Second Screen")
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    MyApp()
}


