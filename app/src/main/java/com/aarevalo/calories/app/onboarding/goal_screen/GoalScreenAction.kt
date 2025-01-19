package com.aarevalo.calories.app.onboarding.goal_screen

import com.aarevalo.calories.core.domain.model.GoalType

sealed interface GoalScreenAction {
    data class OnGoalSelect(val goal: GoalType) : GoalScreenAction
    object OnNextClick : GoalScreenAction
}
