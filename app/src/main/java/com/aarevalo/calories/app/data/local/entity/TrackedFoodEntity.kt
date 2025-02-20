package com.aarevalo.calories.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aarevalo.calories.app.domain.tracker.model.MealType
import com.aarevalo.calories.app.domain.tracker.model.TrackedFood
import java.time.LocalDate

@Entity
data class TrackedFoodEntity(
    val name: String,
    val carbs: Int,
    val protein: Int,
    val fat: Int,
    val imageUrl: String?,
    val type: String,
    val amount: Int,
    val dayOfMonth: Int,
    val month: Int,
    val year: Int,
    val calories: Int,
    @PrimaryKey val id: Int? = null
) {
    fun toTrackedFood() : TrackedFood {
        return TrackedFood(
            name = name,
            carbs = carbs,
            protein = protein,
            fat = fat,
            imageUrl = imageUrl,
            mealType = MealType.fromString(type),
            amount = amount,
            date = LocalDate.of(year, month, dayOfMonth),
            calories = calories
        )
    }

    companion object
    {
        fun fromTrackedFood(trackedFood: TrackedFood) : TrackedFoodEntity {
            return TrackedFoodEntity(
                name = trackedFood.name,
                carbs = trackedFood.carbs,
                protein = trackedFood.protein,
                fat = trackedFood.fat,
                imageUrl = trackedFood.imageUrl,
                type = trackedFood.mealType.name,
                amount = trackedFood.amount,
                dayOfMonth = trackedFood.date.dayOfMonth,
                month = trackedFood.date.monthValue,
                year = trackedFood.date.year,
                calories = trackedFood.calories,
                id = trackedFood.id
                )
        }
    }
}