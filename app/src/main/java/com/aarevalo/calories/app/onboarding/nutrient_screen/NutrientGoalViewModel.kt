package com.aarevalo.calories.app.onboarding.nutrient_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aarevalo.calories.R
import com.aarevalo.calories.core.domain.use_case.FilterOutDigits
import com.aarevalo.calories.core.domain.util.UiEvent
import com.aarevalo.calories.core.domain.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor() : ViewModel(){
    private val filterOutDigits = FilterOutDigits()

    private val _state = MutableStateFlow(NutrientGoalScreenState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAction(action: NutrientGoalScreenAction) {
        viewModelScope.launch {
            when(action) {
                is NutrientGoalScreenAction.OnCarbRatioEnter -> {
                    println(action.ratio)
                    if(action.ratio.length <= 2){
                        _state.update {
                            it.copy(carbsRatio = filterOutDigits(action.ratio))
                        }
                    }
                }
                is NutrientGoalScreenAction.OnProteinRatioEnter -> {
                    if(action.ratio.length <= 2) {
                        _state.update {
                            it.copy(proteinRatio = filterOutDigits(action.ratio))
                        }
                    }
                }
                is NutrientGoalScreenAction.OnFatRatioEnter -> {
                    if(action.ratio.length <= 2) {
                        _state.update {
                            it.copy(fatRatio = filterOutDigits(action.ratio))
                        }
                    }
                }
                is NutrientGoalScreenAction.OnNextClick -> {
                    val carbsRatio = state.value.carbsRatio.toIntOrNull()
                    val proteinRatio = state.value.proteinRatio.toIntOrNull()
                    val fatRatio = state.value.fatRatio.toIntOrNull()

                    println("carbsRatio: $carbsRatio, proteinRatio: $proteinRatio, fatRatio: $fatRatio")

                    if(carbsRatio == null || proteinRatio == null || fatRatio == null) {
                        _uiEvent.send(
                            UiEvent.ShowSnackbar(
                                UiText.StringResource(R.string.error_invalid_values)
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
