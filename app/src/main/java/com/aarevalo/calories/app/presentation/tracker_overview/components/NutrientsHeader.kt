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
import com.aarevalo.calories.app.presentation.tracker_overview.TrackerOverviewScreenState
import com.aarevalo.calories.app.ui.theme.CaloriesTheme
import com.aarevalo.calories.app.ui.theme.FatColor
import com.aarevalo.calories.app.ui.theme.LocalSpacing
import com.aarevalo.calories.app.ui.theme.ProteinColor
import com.aarevalo.calories.app.ui.theme.tertiaryDark

@Composable
fun NutrientsHeader(
    state: TrackerOverviewScreenState,
    modifier: Modifier = Modifier
){
    val spacing = LocalSpacing.current
    val animatedCalorieCount = animateIntAsState(
        targetValue = state.totalCalories
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
                value = state.totalCarbs,
                goal = state.carbsGoal,
                name = stringResource(R.string.carbs),
                color = tertiaryDark,
                modifier = Modifier.size(90.dp)
            )

            NutrientBarInfo(
                value = state.totalProtein,
                goal = state.proteinGoal,
                name = stringResource(R.string.protein),
                color = ProteinColor,
                modifier = Modifier.size(90.dp)
            )

            NutrientBarInfo(
                value = state.totalFat,
                goal = state.fatGoal,
                name = stringResource(R.string.fat),
                color = FatColor,
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
                unit = stringResource(R.string.kcal),
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
                    amount = state.caloriesGoal,
                    unit = stringResource(R.string.kcal),
                    amountColor = MaterialTheme.colorScheme.onPrimary,
                    amountTextSize = 40.sp,
                    unitColor = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }

        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        NutrientsBar(
            carbs = state.totalCarbs,
            proteins = state.totalProtein,
            fats = state.totalFat,
            calories = state.totalCalories,
            calorieGoal = state.caloriesGoal,
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NutrientsHeaderPreview() {
    CaloriesTheme {
        NutrientsHeader(
            state = TrackerOverviewScreenState()
        )
    }
}