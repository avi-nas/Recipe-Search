package com.example.recipesearch.presentation.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WelcomeViewModel @Inject constructor() : ViewModel() {
    // Add your state and logic here
    fun fetchData() {
        viewModelScope.launch {
            // Fetch data
        }
    }
}