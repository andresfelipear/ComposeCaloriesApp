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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.aarevalo.calories.app.presentation.tracker_overview.components.DaySelector
import com.aarevalo.calories.app.presentation.tracker_overview.components.ExpandableMeal
import com.aarevalo.calories.app.presentation.tracker_overview.components.NutrientsHeader
import com.aarevalo.calories.app.presentation.tracker_overview.model.defaultMeals
import com.aarevalo.calories.app.ui.theme.CaloriesTheme
import com.aarevalo.calories.app.ui.theme.LocalSpacing
import com.aarevalo.calories.core.domain.util.UiEvent
import java.time.LocalDate

@Composable
fun TrackerOverviewScreenRoot(
    viewModel: TrackerOverviewViewModel = hiltViewModel(),
    onNavigateToSearch: () -> Unit
) {

    LaunchedEffect(true) {
        viewModel.event.collect { event ->
            when(event) {
                is UiEvent.Success -> onNavigateToSearch()
                else -> Unit
            }
        }
    }

    TrackerOverviewScreen(
        onAction = { action ->
            when(action) {
                is TrackerOverviewScreenAction.OnNavigateToSearch -> viewModel.onAction(action)
                else -> Unit
            }
        }
    )
}

@Composable
fun TrackerOverviewScreen(
    onAction: (TrackerOverviewScreenAction) -> Unit = {}
) {

    val spacing = LocalSpacing.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = spacing.spaceMedium)
    ) {
        item {
            NutrientsHeader()
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
        items(defaultMeals){ meal ->
            ExpandableMeal(
                meal = meal,
                onToggleClick = {
                    onAction(TrackerOverviewScreenAction.OnNavigateToSearch(meal))
                },
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = spacing.spaceSmall)
                    ) {
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