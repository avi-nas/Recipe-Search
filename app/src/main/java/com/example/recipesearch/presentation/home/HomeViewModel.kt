package com.example.recipesearch.presentation.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesearch.models.Recipes
import com.example.recipesearch.network.SpoonacularApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    // Add your state and logic here

    var navScreen = mutableStateOf(NavItem.home)
    var recipes: MutableState<Recipes?> = mutableStateOf(null)
    var isDataLoaded = mutableStateOf(false)

    fun fetchData() {
        viewModelScope.launch {
            // Fetch data
            val response = SpoonacularApi().getRecipes()
            if(response.isSuccess){
                recipes.value = response.getOrNull()
                isDataLoaded.value = true
                Log.d("TAG", "fetchData: ${response.getOrNull()}")
            }else{
                Log.d("TAG","fetchData error: ${response.exceptionOrNull()}")
            }
        }
    }
    fun gotoFav(){
        navScreen.value = NavItem.favourite
    }
    fun gotoHome(){
        navScreen.value = NavItem.home
    }
}