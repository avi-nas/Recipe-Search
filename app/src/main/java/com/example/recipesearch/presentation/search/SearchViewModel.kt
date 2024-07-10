package com.example.recipesearch.presentation.search;

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipesearch.models.recipe.Recipe
import com.example.recipesearch.network.SpoonacularApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel  @Inject constructor(
) : ViewModel() {

    var recipe: MutableState<Recipe?> = mutableStateOf(null)
    var isDataLoaded = mutableStateOf(false)

    fun fetchData(id:Int) {
        Log.d("TAG", "fetchData: id:$id")
        viewModelScope.launch {
            val response = SpoonacularApi().getRecipeData(id)
            if(response.isSuccess) {
                recipe.value = response.getOrNull()
                isDataLoaded.value = true
                Log.d("TAG", "fetchData: ${response.getOrNull()}")
            }else{
                Log.d("TAG", "fetchData: ${response.exceptionOrNull()}")
            }
        }
    }

    fun showButtomSheet(){
        viewModelScope.launch {

        }
    }
}
