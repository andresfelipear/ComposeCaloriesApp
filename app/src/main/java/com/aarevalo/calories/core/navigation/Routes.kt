package com.aarevalo.calories.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object WelcomeScreenRoute
@Serializable
object GenderScreenRoute
@Serializable
object AgeScreenRoute
@Serializable
object HeightScreenRoute
@Serializable
object WeightScreenRoute
@Serializable
object ActivityLevelScreenRoute
@Serializable
object GoalScreenRoute
@Serializable
object NutrientGoalScreenRoute
@Serializable
object TrackerOverviewScreenRoute
@Serializable
data class SearchScreenRoute(
    val mealName: String,
    val dayOfMonth: Int,
    val month: Int,
    val year: Int,
)

