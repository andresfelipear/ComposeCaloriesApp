package com.aarevalo.calories.app.presentation.tracker_overview.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aarevalo.calories.R
import com.aarevalo.calories.app.ui.theme.CaloriesTheme

@Composable
fun NutrientBarInfo(
    value: Int,
    goal: Int,
    name: String,
    color: Color,
    modifier: Modifier = Modifier,
){
    val background = MaterialTheme.colorScheme.onSurfaceVariant
    val goalExceededColor = MaterialTheme.colorScheme.error
    val angleRatio = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = value){
        angleRatio.animateTo(
            targetValue = if (goal > 0) {
                value / goal.toFloat()
            } else 0f,
            animationSpec = tween(
                durationMillis = 300
            )
        )
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            drawRoundRect(
                color = if (value <= goal) background else goalExceededColor,
                size = size,
                cornerRadius = CornerRadius.Zero
            )
            if(value <= goal) {
                drawRoundRect(
                    color = color,
                    size = Size(
                        width = size.width * angleRatio.value,
                        height = size.height
                    ),
                    cornerRadius = CornerRadius.Zero
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UnitDisplay(
                amount = value,
                unit = stringResource(R.string.grams),
                amountColor = if (value <= goal) {
                    MaterialTheme.colorScheme.onSecondary
                } else goalExceededColor,
                unitColor = if (value <= goal) {
                    MaterialTheme.colorScheme.onSecondary
                } else goalExceededColor
            )

            Text(
               text = name,
                color = if (value <= goal) {
                    MaterialTheme.colorScheme.onSecondary
                } else goalExceededColor,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Light
            )
        }
    }

}

@Preview (showBackground = true)
@Composable
fun NutrientBarInfoPreview() {
    CaloriesTheme {
        NutrientBarInfo(
            value = 100,
            goal = 200,
            name = "Carbs",
            color = Color.Red,
            modifier = Modifier.size(90.dp)
        )
    }
}