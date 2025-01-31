package com.aarevalo.calories.app.presentation.search.model

import com.aarevalo.calories.app.domain.tracker.model.TrackableFood

data class TrackableFoodUiState(
    val food: TrackableFood,
    val isExpanded: Boolean = false,
    val amount: String = ""
)
