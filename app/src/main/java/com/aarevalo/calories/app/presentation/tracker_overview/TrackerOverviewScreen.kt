package com.aarevalo.calories.app.presentation.tracker_overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.aarevalo.calories.app.presentation.search.components.AddButton
import com.aarevalo.calories.app.presentation.tracker_overview.components.DaySelector
import com.aarevalo.calories.app.presentation.tracker_overview.components.ExpandableMeal
import com.aarevalo.calories.app.presentation.tracker_overview.components.NutrientsHeader
import com.aarevalo.calories.app.presentation.tracker_overview.components.TrackedFoodItem
import com.aarevalo.calories.app.ui.theme.CaloriesTheme
import com.aarevalo.calories.app.ui.theme.LocalSpacing
import java.time.LocalDate

@Composable
fun TrackerOverviewScreenRoot(
    viewModel: TrackerOverviewViewModel = hiltViewModel(),
    onNavigateToSearch: (String, Int, Int, Int) -> Unit
) {

    val state by viewModel.state.collectAsState()
    val context = LocalContext.current


    TrackerOverviewScreen(
        onAction = { action ->
            when(action) {
                is TrackerOverviewScreenAction.OnNavigateToSearch -> {
                    onNavigateToSearch(
                        action.meal.name.asString(context = context),
                        state.date.dayOfMonth,
                        state.date.monthValue,
                        state.date.year
                    )
                }
                else -> viewModel.onAction(action)
            }
        },
        state = state
    )
}

@Composable
fun TrackerOverviewScreen(
    onAction: (TrackerOverviewScreenAction) -> Unit = {},
    state: TrackerOverviewScreenState = TrackerOverviewScreenState()
) {

    val spacing = LocalSpacing.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = spacing.spaceMedium)
    ) {
        item {
            NutrientsHeader(state = state)
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            DaySelector(
                date = LocalDate.now(),
                onPreviousDayClick = {},
                onNextDayClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceMedium)
            )
        }
        items(state.meals){ meal ->
            ExpandableMeal(
                meal = meal,
                onToggleClick = {
                    onAction(TrackerOverviewScreenAction.OnToggleMealClick(meal))
                },
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = spacing.spaceSmall)
                    ) {
                        val foods = state.trackedFoods.filter {
                            it.mealType == meal.mealType
                        }
                        foods.forEach { food ->
                            TrackedFoodItem(
                                trackedFood = food,
                                onDeleteItem = {}
                            )
                           Spacer(modifier = Modifier.height(spacing.spaceMedium))
                        }
                        AddButton(
                            text = "Add",
                            onClick = {
                                onAction(
                                    TrackerOverviewScreenAction.OnNavigateToSearch(meal)
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrackerOverviewScreenPreview() {
    CaloriesTheme {
        TrackerOverviewScreen()
    }
}