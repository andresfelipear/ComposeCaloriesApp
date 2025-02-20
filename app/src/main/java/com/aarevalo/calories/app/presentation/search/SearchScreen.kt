package com.aarevalo.calories.app.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.aarevalo.calories.R
import com.aarevalo.calories.app.domain.tracker.model.MealType
import com.aarevalo.calories.app.presentation.search.components.SearchTextField
import com.aarevalo.calories.app.presentation.search.components.TrackableFoodItem
import com.aarevalo.calories.app.ui.theme.CaloriesTheme
import com.aarevalo.calories.app.ui.theme.LocalSpacing
import com.aarevalo.calories.core.domain.util.UiEvent
import java.time.LocalDate

@Composable
fun SearchScreenRoot(
    viewModel: SearchScreenViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit,
    snackBarHostState: SnackbarHostState,
) {
    val state by viewModel.state.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    LaunchedEffect(key1 = keyboardController) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.ShowSnackbar -> {
                    snackBarHostState.showSnackbar(message = event.message.asString(context))
                    keyboardController?.hide()
                }
                is UiEvent.NavigateUp -> {
                    onNavigateUp()
                }
                else -> Unit
            }
        }
    }
    SearchScreen(
        mealName = "Meal Name",
        onAction = viewModel::onAction,
        state = state,
        year = LocalDate.now().year,
        month = LocalDate.now().monthValue,
        dayOfMonth = LocalDate.now().dayOfMonth
    )
}

@Composable
fun SearchScreen(
    mealName: String,
    onAction: (SearchScreenAction) -> Unit,
    state: SearchScreenState,
    year: Int,
    month: Int,
    dayOfMonth: Int
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium)
    ) {
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        Text(
            text = stringResource(id = R.string.add_meal, mealName),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        SearchTextField(
            text = state.searchQuery,
            onValueChange = {
                onAction(SearchScreenAction.OnQueryChange(it))
            },
            shouldShowHint = state.isHintVisible,
            onSearch = {
                onAction(SearchScreenAction.OnSearch)
                keyboardController?.hide()
            },
            onFocusChanged = {
                onAction(SearchScreenAction.OnSearchFocusChange(it.isFocused))
            }
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.trackableFood){ food ->
                TrackableFoodItem(
                    state = food,
                    onClick = {
                        onAction(SearchScreenAction.OnToggleTrackableFood(food.food))
                    },
                    onAmountChange = {
                        onAction(
                            SearchScreenAction.OnAmountForFoodChange(
                                food = food.food,
                                amount = it
                            )
                        )
                    },
                    onTrack = {
                        keyboardController?.hide()
                        onAction(
                            SearchScreenAction.OnTrackFoodClick(
                                food = food.food,
                                mealType = MealType.fromString(mealName),
                                date = LocalDate.of(year, month, dayOfMonth)
                            )
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            state.isSearching -> CircularProgressIndicator()
            state.trackableFood.isEmpty() -> {
                Text(
                    text = stringResource(id = R.string.no_results),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview(){
    CaloriesTheme {
        SearchScreen(
            mealName = "Breakfast",
            onAction = {},
            state = SearchScreenState(),
            year = LocalDate.now().year,
            month = LocalDate.now().monthValue,
            dayOfMonth = LocalDate.now().dayOfMonth
        )
    }
}