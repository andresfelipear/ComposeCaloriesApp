package com.aarevalo.calories.app.onboarding.gender_screen

import com.aarevalo.calories.core.domain.model.Gender

sealed interface GenderScreenAction {
    data class OnGenderClick(val gender: Gender) : GenderScreenAction
    data object OnNextClick : GenderScreenAction
}