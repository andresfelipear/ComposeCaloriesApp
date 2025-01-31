package com.aarevalo.calories.app.presentation.tracker_overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aarevalo.calories.app.domain.tracker.usecases.TrackerUseCases
import com.aarevalo.calories.core.domain.preferences.Preferences
import com.aarevalo.calories.core.domain.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
    preferences: Preferences,
    private val trackerUseCases: TrackerUseCases
) : ViewModel() {

    private val _event = Channel<UiEvent>()
    val event = _event.receiveAsFlow()

    fun onAction(action: TrackerOverviewScreenAction) {
        viewModelScope.launch {
            when(action) {
                is TrackerOverviewScreenAction.OnNavigateToSearch -> {
                    _event.send(UiEvent.Success)
                }
                else -> Unit
            }
        }
    }

    init {
        println("Inside the tracker overview view model")
        println("Should Show onboarding: false")
        preferences.saveShouldShowOnboarding(false)
        executeSearch()
    }

    private fun executeSearch(){
        viewModelScope.launch {
            trackerUseCases.searchFoodUseCase("egg")
        }
    }
}