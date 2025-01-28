package com.aarevalo.calories.app.presentation.tracker_overview.components

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SuppressLint("ComposableNaming")
@Composable
fun ParseDateText(
    date: LocalDate
): String {
    val today = LocalDate.now()
    return when(date){
        today -> "Today"
        today.minusDays(1) -> "Yesterday"
        today.plusDays(1) -> "Tomorrow"
        else -> DateTimeFormatter.ofPattern("dd LLLL").format(date) // 01 January
    }
}