package com.aarevalo.calories.app.presentation.onboarding.height_screen

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
class HeightViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits
) : ViewModel() {

    private val _state = MutableStateFlow(HeightScreenState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAction(action: HeightScreenAction) {
        viewModelScope.launch {
            when(action) {
                is HeightScreenAction.OnHeightEnter -> {
                    if(action.height.length <= 3) {
                        _state.update {
                            it.copy(height = filterOutDigits(action.height))
                        }
                    }
                }
                is HeightScreenAction.OnNextClick -> {
                    val heightNumber = state.value.height.toIntOrNull() ?: kotlin.run {
                        _uiEvent.send(
                            UiEvent.ShowSnackbar(
                                UiText.StringResource(R.string.error_height_cant_be_empty)
                            )
                        )
                        return@launch
                    }
                    preferences.saveHeight(heightNumber)
                    _uiEvent.send(UiEvent.Success)
                }
            }
        }
    }
}
