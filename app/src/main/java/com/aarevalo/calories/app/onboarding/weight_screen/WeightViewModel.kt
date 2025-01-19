package com.aarevalo.calories.app.onboarding.weight_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aarevalo.calories.R
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
class WeightViewModel @Inject constructor() : ViewModel() {

    private val filterOutDigits = FilterOutDigits()

    private val _state = MutableStateFlow(WeightScreenState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAction(action: WeightScreenAction) {
        viewModelScope.launch {
            when(action) {
                is WeightScreenAction.OnWeightEnter -> {
                    if(action.weight.length <= 3) {
                        _state.update {
                            it.copy(weight = filterOutDigits(action.weight))
                        }
                    }
                }
                is WeightScreenAction.OnNextClick -> {
                    state.value.weight.toIntOrNull() ?: kotlin.run {
                        _uiEvent.send(
                            UiEvent.ShowSnackbar(
                                UiText.StringResource(R.string.error_weight_cant_be_empty)
                            )
                        )
                        return@launch
                    }
                    _uiEvent.send(UiEvent.Success)
                }
            }
        }
    }
}
