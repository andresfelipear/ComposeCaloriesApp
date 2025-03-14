package com.aarevalo.calories.app.presentation.onboarding.gender_screen

import com.aarevalo.calories.core.domain.model.Gender

data class GenderScreenState(
    val selectedGender: Gender = Gender.Male,
)
