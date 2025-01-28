package com.aarevalo.calories.app.presentation.tracker_overview.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aarevalo.calories.app.ui.theme.CaloriesTheme
import com.aarevalo.calories.app.ui.theme.FatColor
import com.aarevalo.calories.app.ui.theme.ProteinColor
import com.aarevalo.calories.app.ui.theme.tertiaryDark

@Composable
fun NutrientsBar(
    carbs: Int,
    proteins: Int,
    fats: Int,
    calories: Int,
    calorieGoal: Int,
    modifier: Modifier = Modifier
) {
    val background = MaterialTheme.colorScheme.background
    val caloriesExceedColor = MaterialTheme.colorScheme.error
    val carbWidthRatio = remember {
        Animatable(0f)
    }
    val proteinWidthRatio = remember {
        Animatable(0f)
    }
    val fatWidthRatio = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = carbs) {
        carbWidthRatio.animateTo(
            targetValue = ((carbs * 4f) / calorieGoal)
        )
    }
    LaunchedEffect(key1 = proteins) {
        proteinWidthRatio.animateTo(
            targetValue = ((proteins * 4f) / calorieGoal)
        )
    }
    LaunchedEffect(key1 = fats) {
        fatWidthRatio.animateTo(
            targetValue = ((fats * 9f) / calorieGoal)
        )
    }

    Canvas(
        modifier = modifier
    ) {
        if(calories <= calorieGoal) {
            val carbsWidth = carbWidthRatio.value * size.width
            val proteinsWidth = proteinWidthRatio.value * size.width
            val fatsWidth = fatWidthRatio.value * size.width
            drawRoundRect(
                color = background,
                size = size,
                cornerRadius = CornerRadius(20f)
            )
            drawRoundRect(
                color = FatColor,
                size = size.copy(
                    width = carbsWidth + proteinsWidth + fatsWidth,
                    height = size.height
                ),
                cornerRadius = CornerRadius(20f)
            )
            drawRoundRect(
                color = ProteinColor,
                size = size.copy(
                    width = carbsWidth + proteinsWidth,
                    height = size.height
                    ),
                cornerRadius = CornerRadius(20f)
            )
            drawRoundRect(
                color = tertiaryDark,
                size = size.copy(
                    width = carbsWidth,
                    height = size.height
                    ),
                cornerRadius = CornerRadius(20f)
            )
        } else {
            drawRoundRect(
                color = caloriesExceedColor,
                size = size,
                cornerRadius = CornerRadius(20f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NutrientsBarPreview() {
    CaloriesTheme {
        NutrientsBar(
            carbs = 100,
            proteins = 100,
            fats = 100,
            calories = 1000,
            calorieGoal = 2000,
            modifier = Modifier.size(200.dp)
        )
    }
}