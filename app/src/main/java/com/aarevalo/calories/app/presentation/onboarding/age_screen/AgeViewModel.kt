package com.aarevalo.calories.app.presentation.onboarding.age_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aarevalo.calories.R
import com.aarevalo.calories.core.domain.preferences.Preferences
import com.aarevalo.calories.core.domain.use_case.FilterOutDigits
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
class AgeViewModel @Inject constructor(
    private val filterOutDigits: FilterOutDigits,
    private val preferences: Preferences
): ViewModel() {

    private val _state = MutableStateFlow(AgeScreenState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAction(action: AgeScreenAction) {
        viewModelScope.launch {
            when (action) {
                is AgeScreenAction.OnAgeEnter -> {
                    if (action.age.length <= 3) {
                        _state.update {
                            it.copy(
                                age = filterOutDigits(action.age)
                            )
                        }
                    }
                }
                is AgeScreenAction.OnNextClick -> {
                    val ageNumber = state.value.age.toIntOrNull() ?: kotlin.run {
                        _uiEvent.send(
                            UiEvent.ShowSnackbar(
                                UiText.StringResource(R.string.error_age_cant_be_empty)
                            )
                        )
                        return@launch
                    }
                    preferences.saveAge(ageNumber)
                    _uiEvent.send(UiEvent.Success)
                }
            }
        }
    }
}