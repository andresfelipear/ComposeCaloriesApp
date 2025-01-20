package com.aarevalo.calories.app.presentation.onboarding.nutrient_screen

sealed interface NutrientGoalScreenAction{
    object OnNextClick : NutrientGoalScreenAction
    data class OnCarbRatioEnter(val ratio : String) : NutrientGoalScreenAction
    data class OnProteinRatioEnter(val ratio : String) : NutrientGoalScreenAction
    data class OnFatRatioEnter(val ratio : String) : NutrientGoalScreenAction
}
