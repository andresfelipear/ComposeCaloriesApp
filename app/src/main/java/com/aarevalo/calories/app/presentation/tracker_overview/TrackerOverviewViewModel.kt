package com.aarevalo.calories.app.presentation.tracker_overview

import androidx.lifecycle.ViewModel
import com.aarevalo.calories.core.domain.preferences.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
    preferences: Preferences
) : ViewModel() {

    init {
        println("Inside the tracker overview view model")
        println("Should Show onboarding: false")
        preferences.saveShouldShowOnboarding(false)
    }
}