package com.aarevalo.calories.app.presentation.search

import com.aarevalo.calories.app.domain.tracker.model.MealType
import com.aarevalo.calories.app.domain.tracker.model.TrackableFood
import java.time.LocalDate

sealed interface SearchScreenAction {
    data class OnSearchFocusChange(val isFocused: Boolean) : SearchScreenAction
    data class OnDisplayAddCustomItem(val isVisible: Boolean) : SearchScreenAction
    data class OnQueryChange(val query: String) : SearchScreenAction
    data class OnToggleTrackableFood(val food: TrackableFood) : SearchScreenAction
    data object OnNavigateUp : SearchScreenAction
    data object OnSearch : SearchScreenAction
    data class OnAmountForFoodChange(
        val food: TrackableFood,
        val amount: String
    ) : SearchScreenAction
    data class OnTrackFoodClick(
        val food: TrackableFood,
        val mealType: MealType,
        val date: LocalDate
    ) : SearchScreenAction

}
