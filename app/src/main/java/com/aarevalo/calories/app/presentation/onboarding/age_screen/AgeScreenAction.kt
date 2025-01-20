package com.aarevalo.calories.app.presentation.onboarding.age_screen

sealed interface AgeScreenAction {
    data class OnAgeEnter(val age: String): AgeScreenAction
    data object OnNextClick: AgeScreenAction
}
