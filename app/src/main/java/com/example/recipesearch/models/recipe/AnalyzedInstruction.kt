package com.example.recipesearch.models.recipe

data class AnalyzedInstruction(
    val name: String,
    val steps: List<Step>
)