package com.aarevalo.calories.app.domain.tracker.repository

import com.aarevalo.calories.app.domain.tracker.model.TrackableFood

interface TrackerRepository {

    suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>>
}