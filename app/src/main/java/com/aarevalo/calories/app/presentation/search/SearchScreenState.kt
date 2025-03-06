package com.aarevalo.calories.app.presentation.search

import com.aarevalo.calories.app.domain.tracker.model.TrackableFood
import com.aarevalo.calories.app.presentation.search.model.TrackableFoodUiState

data class SearchScreenState(
    val searchQuery: String = "",
    val isHintVisible: Boolean = false,
    val isSearching: Boolean = false,
    val isAddCustomItemVisible: Boolean = false,
    val trackableFood: List<TrackableFoodUiState> = emptyList(),
    val customFoodItem: TrackableFoodUiState =
        TrackableFoodUiState(
        food =
        TrackableFood(
            name = "",
            imageUrl = "",
            caloriesPer100g = 0,
            carbsPer100g = 0,
            proteinPer100g = 0,
            fatPer100g = 0
        ),
        amount = "",
        isExpanded = false)
)
