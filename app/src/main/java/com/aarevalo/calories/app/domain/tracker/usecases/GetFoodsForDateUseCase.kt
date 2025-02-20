package com.aarevalo.calories.app.domain.tracker.usecases

import com.aarevalo.calories.app.domain.tracker.model.TrackedFood
import com.aarevalo.calories.app.domain.tracker.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetFoodsForDateUseCase(
    private val trackerRepository: TrackerRepository
) {
    operator fun invoke(date: LocalDate): Flow<List<TrackedFood>> {
        return trackerRepository.getFoodsForDate(date)
    }
}