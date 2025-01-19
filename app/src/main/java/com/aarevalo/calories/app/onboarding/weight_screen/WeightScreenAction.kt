package com.aarevalo.calories.app.onboarding.weight_screen

sealed interface WeightScreenAction {
    data class OnWeightEnter(val weight: String) : WeightScreenAction
    object OnNextClick : WeightScreenAction
}
