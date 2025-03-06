package com.aarevalo.calories.app.presentation.search

import com.aarevalo.calories.app.presentation.search.model.TrackableFoodUiState

data class SearchScreenState(
    val searchQuery: String = "",
    val isHintVisible: Boolean = false,
    val isSearching: Boolean = false,
    val isAddCustomItemVisible: Boolean = false,
    val trackableFood: List<TrackableFoodUiState> = emptyList()
)
