package com.aarevalo.calories.app.data.remote.repository

import com.aarevalo.calories.app.data.remote.api.OpenFoodApi
import com.aarevalo.calories.app.data.remote.mapper.toTrackableFood
import com.aarevalo.calories.app.domain.tracker.model.TrackableFood
import com.aarevalo.calories.app.domain.tracker.repository.TrackerRepository

class DefaultTrackerRepository(
    private val api: OpenFoodApi
) : TrackerRepository {
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {
            val searchDto = api.searchFood(
                query = query,
                page = page,
                pageSize = pageSize
            )
            Result.success(
                searchDto.products
                    .filter {
                        val calculatedCalories =
                            it.nutriments.carbohydrates100g * 4f +
                            it.nutriments.proteins100g * 4f +
                            it.nutriments.fat100g * 9f
                        val lowerBound = calculatedCalories * 0.99f
                        val upperBound = calculatedCalories * 1.01f
                        it.nutriments.energyKcal100g in (lowerBound..upperBound)
                    }
                    .mapNotNull { it.toTrackableFood() }
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

}