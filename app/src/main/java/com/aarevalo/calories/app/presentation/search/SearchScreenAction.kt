package com.aarevalo.calories.app.presentation.search

sealed interface SearchScreenAction {
    data object OnSearch : SearchScreenAction
    data class OnFocusChange(val isFocused: Boolean) : SearchScreenAction
    data class OnQueryChange(val query: String) : SearchScreenAction
    data object OnNavigateUp : SearchScreenAction
}
