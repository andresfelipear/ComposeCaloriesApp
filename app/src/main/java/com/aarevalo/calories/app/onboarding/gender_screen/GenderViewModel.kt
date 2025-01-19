package com.aarevalo.calories.app.onboarding.gender_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aarevalo.calories.core.domain.preferences.Preferences
import com.aarevalo.calories.core.domain.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {

    private val _state = MutableStateFlow(GenderScreenState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAction(action: GenderScreenAction) {
        viewModelScope.launch {
            when (action) {
                is GenderScreenAction.OnGenderClick -> {
                    _state.update {
                        it.copy(
                            selectedGender = action.gender,
                        )
                    }
                }
                is GenderScreenAction.OnNextClick -> {
                    preferences.saveGender(state.value.selectedGender)
                    _uiEvent.send(UiEvent.Success)
                }
            }
        }
    }
}