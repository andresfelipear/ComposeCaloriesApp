package com.aarevalo.calories.app.presentation.tracker_overview.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.aarevalo.calories.R
import com.aarevalo.calories.app.domain.tracker.model.MealType
import com.aarevalo.calories.app.presentation.tracker_overview.model.Meal
import com.aarevalo.calories.app.ui.theme.CaloriesTheme
import com.aarevalo.calories.app.ui.theme.LocalSpacing
import com.aarevalo.calories.core.domain.util.UiText

@Composable
fun ExpandableMeal(
    meal: Meal,
    onToggleClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onToggleClick()
                }
                .padding(spacing.spaceMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = meal.drawableRes),
                contentDescription = meal.name.asString(context)
            )
            Spacer(modifier = Modifier.width(spacing.spaceMedium))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = meal.name.asString(context),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Icon(
                        imageVector = if (meal.isExpanded) {
                            Icons.Default.KeyboardArrowUp
                        } else {
                            Icons.Default.KeyboardArrowDown
                        },
                        contentDescription = if (meal.isExpanded) {
                            stringResource(R.string.collapse)
                        } else {
                            stringResource(R.string.extend)
                        }
                    )
                }

                Spacer(modifier = modifier.height(spacing.spaceSmall))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    UnitDisplay(
                        amount = meal.calories,
                        unit = "kcal",
                        amountTextSize = MaterialTheme.typography.bodyMedium.fontSize,
                        unitColor = MaterialTheme.colorScheme.onSurface,
                        amountColor = MaterialTheme.colorScheme.onSurface
                    )

                    Row{
                        NutrientInfo(
                            name = stringResource(R.string.carbs),
                            amount = meal.carbs,
                            unit = stringResource(R.string.grams)
                        )
                        Spacer(modifier = Modifier.width(spacing.spaceSmall))
                        NutrientInfo(
                            name = stringResource(R.string.protein),
                            amount = meal.protein,
                            unit = stringResource(R.string.grams)
                        )
                        Spacer(modifier = Modifier.width(spacing.spaceSmall))
                        NutrientInfo(
                            name = stringResource(R.string.fat),
                            amount = meal.fat,
                            unit = stringResource(R.string.grams)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        if (meal.isExpanded) {
            content()
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ExpandableMealPreview() {
    CaloriesTheme {
        ExpandableMeal(
            meal = Meal(
                name = UiText.StringResource(R.string.breakfast),
                drawableRes = R.drawable.breakfast_02,
                mealType = MealType.Breakfast,
                calories = 500,
                carbs = 50,
                protein = 20,
            ),
            onToggleClick = {},
            content = {}
        )
    }
}