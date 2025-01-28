package com.aarevalo.calories.app.presentation.tracker_overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aarevalo.calories.app.domain.tracker.usecases.TrackerUseCase
import com.aarevalo.calories.core.domain.preferences.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
    preferences: Preferences,
    private val trackerUseCase: TrackerUseCase
) : ViewModel() {

    init {
        println("Inside the tracker overview view model")
        println("Should Show onboarding: false")
        preferences.saveShouldShowOnboarding(false)
        executeSearch()
    }

    private fun executeSearch(){
        viewModelScope.launch {
            trackerUseCase.searchFoodUseCase("egg")
        }
    }
}