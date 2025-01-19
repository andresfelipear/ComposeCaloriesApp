package com.aarevalo.calories.app.onboarding.activity_level_screen

import com.aarevalo.calories.core.domain.model.ActivityLevel

sealed interface ActivityLevelScreenAction {
    object OnNextClick : ActivityLevelScreenAction
    data class OnActivityLevelSelect(val activityLevel: ActivityLevel) : ActivityLevelScreenAction
}
