package com.aarevalo.calories.app.presentation.onboarding.activity_level_screen

import com.aarevalo.calories.core.domain.model.ActivityLevel

data class ActivityLevelScreenState(
    val selectedActivityLevel: ActivityLevel = ActivityLevel.Medium,
)
