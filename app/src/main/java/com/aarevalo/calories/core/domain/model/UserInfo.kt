package com.aarevalo.calories.core.domain.model

import com.aarevalo.calories.core.domain.model.ActivityLevel
import com.aarevalo.calories.core.domain.model.Gender
import com.aarevalo.calories.core.domain.model.GoalType

data class UserInfo(
    val gender: Gender,
    val age: Int,
    val weight: Float,
    val height: Int,
    val activityLevel: ActivityLevel,
    val goalType: GoalType,
    val carbRatio: Float,
    val proteinRatio: Float,
    val fatRatio: Float
)
