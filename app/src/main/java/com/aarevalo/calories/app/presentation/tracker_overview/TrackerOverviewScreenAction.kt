package com.aarevalo.calories.app.presentation.tracker_overview

import com.aarevalo.calories.app.domain.tracker.model.TrackedFood
import com.aarevalo.calories.app.presentation.tracker_overview.model.Meal

sealed interface TrackerOverviewScreenAction {
    data object OnNextDayClick : TrackerOverviewScreenAction
    data object OnPreviousDayClick : TrackerOverviewScreenAction
    data class OnNavigateToSearch(val meal: Meal) : TrackerOverviewScreenAction
    data class OnToggleMealClick(val meal: Meal) : TrackerOverviewScreenAction
    data class OnDeleteTrackedFoodClick(val trackedFood: TrackedFood) : TrackerOverviewScreenAction
}
