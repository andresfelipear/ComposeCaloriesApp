package com.aarevalo.calories.app.presentation.tracker_overview

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aarevalo.calories.app.domain.tracker.usecases.TrackerUseCases
import com.aarevalo.calories.core.domain.preferences.Preferences
import com.aarevalo.calories.core.domain.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
    preferences: Preferences,
    private val trackerUseCases: TrackerUseCases
) : ViewModel() {

    private val _event = Channel<UiEvent>()
    val event = _event.receiveAsFlow()

    private val _state = MutableStateFlow(TrackerOverviewScreenState())
    val state = _state.asStateFlow()

    fun onAction(action: TrackerOverviewScreenAction) {
        viewModelScope.launch {
            when(action) {
                is TrackerOverviewScreenAction.OnNavigateToSearch -> {
                    _event.send(UiEvent.Success)
                }
                is TrackerOverviewScreenAction.OnToggleMealClick -> {
                    _state.update {
                        it.copy(
                            meals = state.value.meals.map { meal ->
                                if(meal.name == action.meal.name) {
                                    meal.copy(isExpanded = !meal.isExpanded)
                                } else meal
                            }
                        )
                    }
                }
                else -> Unit
            }
        }
    }

    init {
        refreshFoods()
        preferences.saveShouldShowOnboarding(false)
    }

    private fun refreshFoods(){
        trackerUseCases
            .getFoodsForDateUseCase(state.value.date)
            .onEach { foods ->
                val nutrientsResult = trackerUseCases.calculateMealNutrientsUseCase(foods)
                _state.update {
                    it.copy(
                        totalCarbs = nutrientsResult.totalCarbs,
                        totalFat = nutrientsResult.totalFat,
                        totalProtein = nutrientsResult.totalProtein,
                        totalCalories = nutrientsResult.totalCalories,
                        carbsGoal = nutrientsResult.carbsGoal,
                        fatGoal = nutrientsResult.fatGoal,
                        proteinGoal = nutrientsResult.proteinGoal,
                        caloriesGoal = nutrientsResult.caloriesGoal,
                        trackedFoods = foods,
                        meals = it.meals.map { meal ->
                            val nutrientsForMeal =
                                nutrientsResult.mealNutrients[meal.mealType]
                                    ?: return@map meal.copy(
                                        carbs = 0,
                                        protein = 0,
                                        fat = 0,
                                        calories = 0
                                    )
                            meal.copy(
                                carbs = nutrientsForMeal.carbs,
                                fat = nutrientsForMeal.fat,
                                protein = nutrientsForMeal.protein,
                                calories = nutrientsForMeal.calories
                            )
                        }
                    )
                }
            }
    }

}