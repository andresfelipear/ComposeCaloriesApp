package com.aarevalo.calories.app.onboarding.height_screen

sealed interface HeightScreenAction {
    data class OnHeightEnter(val height: String): HeightScreenAction
    data object OnNextClick: HeightScreenAction
}
