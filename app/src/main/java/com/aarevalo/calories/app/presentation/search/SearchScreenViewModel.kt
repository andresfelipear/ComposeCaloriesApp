package com.aarevalo.calories.app.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aarevalo.calories.R
import com.aarevalo.calories.app.domain.tracker.usecases.TrackerUseCases
import com.aarevalo.calories.app.presentation.search.model.TrackableFoodUiState
import com.aarevalo.calories.core.domain.util.UiEvent
import com.aarevalo.calories.core.domain.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val trackerUseCases: TrackerUseCases
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _state = MutableStateFlow(SearchScreenState())
    val state = _state.asStateFlow()

    fun onAction(action: SearchScreenAction) {
        viewModelScope.launch {
            when(action) {
                is SearchScreenAction.OnQueryChange -> {
                    _state.update {
                        it.copy(searchQuery = action.query)
                    }
                }
                is SearchScreenAction.OnSearch -> {
                    executeSearch()
                }
                is SearchScreenAction.OnFocusChange -> {
                    _state.update {
                        it.copy(
                            isHintVisible = !action.isFocused && state.value.searchQuery.isBlank()
                        )
                    }
                }
                is SearchScreenAction.OnNavigateUp -> {
                    _uiEvent.send(UiEvent.NavigateUp)
                }
            }
        }
    }

    private suspend fun executeSearch() {
        _state.update {
            it.copy(
                isSearching = true,
                trackableFood = emptyList()
            )
        }
        trackerUseCases.searchFoodUseCase(state.value.searchQuery)
            .onSuccess { foods ->
                _state.update {
                    it.copy(
                        trackableFood = foods.map { food ->
                            TrackableFoodUiState(food)
                        },
                        isSearching = false,
                        searchQuery = "",
                        isHintVisible = true
                    )
                }
            }
            .onFailure {
                _state.update {
                    it.copy(isSearching = false)
                }
                _uiEvent.send(
                    UiEvent.ShowSnackbar(
                        UiText.StringResource(R.string.error_something_went_wrong)
                    )
                )
            }
    }
}
