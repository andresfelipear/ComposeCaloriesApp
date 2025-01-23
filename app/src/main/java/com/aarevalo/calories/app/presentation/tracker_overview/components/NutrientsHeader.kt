package com.aarevalo.calories.app.presentation.tracker_overview.components

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aarevalo.calories.R
import com.aarevalo.calories.app.ui.theme.CaloriesTheme
import com.aarevalo.calories.app.ui.theme.LocalSpacing

@Composable
fun NutrientsHeader(
    modifier: Modifier = Modifier
){
    val spacing = LocalSpacing.current
    val animatedCalorieCount = animateIntAsState(
        targetValue = 2000
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onSurfaceVariant)
            .padding(
                horizontal = spacing.spaceLarge,
                vertical = spacing.spaceExtraLarge
            )
    ) {
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NutrientBarInfo(
                value = 100,
                goal = 200,
                name = "Carbs",
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.size(90.dp)
            )

            NutrientBarInfo(
                value = 50,
                goal = 150,
                name = "Proteins",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.size(90.dp)
            )

            NutrientBarInfo(
                value = 300,
                goal = 500,
                name = "Fats",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(90.dp)
            )
        }
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            UnitDisplay(
                amount = animatedCalorieCount.value,
                unit = "kcal",
                amountColor = MaterialTheme.colorScheme.onPrimary,
                amountTextSize = 40.sp,
                unitColor = MaterialTheme.colorScheme.onPrimary,
            )

            Column {
                Text(
                    text = stringResource(R.string.your_goal),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                UnitDisplay(
                    amount = 2550,
                    unit = "kcal",
                    amountColor = MaterialTheme.colorScheme.onPrimary,
                    amountTextSize = 40.sp,
                    unitColor = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }

    }



}

@Preview(showBackground = true)
@Composable
fun NutrientsHeaderPreview() {
    CaloriesTheme {
        NutrientsHeader()
    }
}