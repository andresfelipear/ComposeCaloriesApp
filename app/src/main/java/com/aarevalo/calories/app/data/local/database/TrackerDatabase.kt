package com.aarevalo.calories.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aarevalo.calories.app.data.local.dao.TrackerDao
import com.aarevalo.calories.app.data.local.entity.TrackedFoodEntity

@Database(
    entities = [TrackedFoodEntity::class],
    version = 1
)
abstract class TrackerDatabase: RoomDatabase() {
    abstract val trackerDao: TrackerDao
}