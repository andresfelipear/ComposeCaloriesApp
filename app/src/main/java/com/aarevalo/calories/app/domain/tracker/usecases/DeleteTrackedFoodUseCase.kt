package com.aarevalo.calories.app.domain.tracker.usecases

import com.aarevalo.calories.app.domain.tracker.model.TrackedFood
import com.aarevalo.calories.app.domain.tracker.repository.TrackerRepository

class DeleteTrackedFoodUseCase(
    private val trackerRepository: TrackerRepository
) {
    suspend operator fun invoke(trackedFood: TrackedFood) {
        trackerRepository.deleteTrackedFood(trackedFood)
    }
}