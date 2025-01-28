package com.aarevalo.calories.app.presentation.tracker_overview

import java.time.LocalDate

data class TrackerOverviewScreenState(
    val currentDay: LocalDate = LocalDate.now(),
)
