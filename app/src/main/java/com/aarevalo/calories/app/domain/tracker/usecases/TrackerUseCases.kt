package com.aarevalo.calories.app.domain.tracker.usecases

data class TrackerUseCases(
    val searchFoodUseCase: SearchFoodUseCase,
    val trackFoodUseCase: TrackFoodUseCase,
    val getFoodsForDateUseCase: GetFoodsForDateUseCase,
    val calculateMealNutrientsUseCase: CalculateMealNutrientsUseCase,
    val deleteTrackedFoodUseCase: DeleteTrackedFoodUseCase
)
