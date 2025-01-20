package com.aarevalo.calories.app.presentation.onboarding.goal_screen

import com.aarevalo.calories.core.domain.model.GoalType

data class GoalScreenState(
    val selectedGoal: GoalType = GoalType.KeepWeight,
)
