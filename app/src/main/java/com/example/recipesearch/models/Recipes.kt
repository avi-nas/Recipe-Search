package com.example.recipesearch.models

data class Recipes(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
)