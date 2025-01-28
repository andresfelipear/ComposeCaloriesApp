package com.aarevalo.calories.app.presentation.tracker_overview

sealed interface TrackerOverviewScreenAction {
    data object OnNextDayClick : TrackerOverviewScreenAction
    data object OnPreviousDayClick : TrackerOverviewScreenAction
    data object OnToggleMealClick : TrackerOverviewScreenAction
}
