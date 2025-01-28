package com.aarevalo.calories.app.domain.tracker.model

sealed class MealType(val name: String) {
    data object Breakfast : MealType("Breakfast")
    data object Lunch : MealType("Lunch")
    data object Dinner : MealType("Dinner")
    data object Snack : MealType("Snack")

    companion object {
        fun fromString(name: String) : MealType {
            return when(name) {
                "Breakfast" -> Breakfast
                "Lunch" -> Lunch
                "Dinner" -> Dinner
                "Snack" -> Snack
                else -> Breakfast
            }
        }
    }
}