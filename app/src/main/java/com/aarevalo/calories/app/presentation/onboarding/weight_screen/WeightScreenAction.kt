package com.aarevalo.calories.app.presentation.onboarding.weight_screen

sealed interface WeightScreenAction {
    data class OnWeightEnter(val weight: String) : WeightScreenAction
    object OnNextClick : WeightScreenAction
}
