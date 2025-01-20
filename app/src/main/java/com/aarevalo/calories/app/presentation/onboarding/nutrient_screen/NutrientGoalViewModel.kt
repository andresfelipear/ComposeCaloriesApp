package com.aarevalo.calories.app.presentation.onboarding.nutrient_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aarevalo.calories.app.domain.use_case.ValidateNutrients
import com.aarevalo.calories.core.domain.preferences.Preferences
import com.aarevalo.calories.core.domain.use_case.FilterOutDigits
import com.aarevalo.calories.core.domain.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits,
    private val validateNutrients: ValidateNutrients
) : ViewModel(){

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
                    val result = validateNutrients(
                        carbsRatioText = state.value.carbsRatio,
                        proteinRatioText = state.value.proteinRatio,
                        fatRatioText = state.value.fatRatio
                    )

                    when(result) {
                        is ValidateNutrients.Result.Success -> {
                            preferences.saveCarbRatio(result.carbsRatio)
                            preferences.saveProteinRatio(result.proteinRatio)
                            preferences.saveFatRatio(result.fatRatio)
                            _uiEvent.send(UiEvent.Success)
                        }
                        is ValidateNutrients.Result.Error -> {
                            _uiEvent.send(UiEvent.ShowSnackbar(result.message))
                        }
                    }
                }
            }
        }
    }
}
