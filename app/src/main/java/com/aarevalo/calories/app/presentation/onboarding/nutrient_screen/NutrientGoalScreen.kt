package com.aarevalo.calories.app.presentation.onboarding.nutrient_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.aarevalo.calories.R
import com.aarevalo.calories.app.presentation.onboarding.components.ActionButton
import com.aarevalo.calories.app.presentation.onboarding.components.UnitTextField
import com.aarevalo.calories.app.ui.theme.LocalSpacing
import com.aarevalo.calories.core.domain.util.UiEvent

@Composable
fun NutrientGoalScreenRoot(
    onNextClick: () -> Unit,
    viewModel: NutrientGoalViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Success -> onNextClick()
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                else -> Unit
            }
        }
    }

    NutrientGoalScreen(
        onAction = {
            when(it){
                is NutrientGoalScreenAction.OnCarbRatioEnter -> viewModel.onAction(it)
                is NutrientGoalScreenAction.OnProteinRatioEnter -> viewModel.onAction(it)
                is NutrientGoalScreenAction.OnFatRatioEnter -> viewModel.onAction(it)
                is NutrientGoalScreenAction.OnNextClick -> viewModel.onAction(it)
            }
        },
        state = state
    )
}
@SuppressLint("UnrememberedMutableState")
@Composable
fun NutrientGoalScreen(
    onAction : (NutrientGoalScreenAction) -> Unit,
    state : NutrientGoalScreenState
) {
    val spacing = LocalSpacing.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(spacing.spaceLarge)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.what_are_your_nutrient_goals)
            )

            Spacer(modifier = Modifier.padding(spacing.spaceMedium))

            UnitTextField(
                value = state.carbsRatio,
                onValueChange = {
                    onAction(NutrientGoalScreenAction.OnCarbRatioEnter(it))
                },
                unit = stringResource(R.string.percent_carbs)
            )

            Spacer(modifier = Modifier.padding(spacing.spaceMedium))

            UnitTextField(
                value = state.proteinRatio,
                onValueChange = {
                    onAction(NutrientGoalScreenAction.OnProteinRatioEnter(it))
                },
                unit = stringResource(R.string.percent_proteins)
            )

            Spacer(modifier = Modifier.padding(spacing.spaceMedium))

            UnitTextField(
                value = state.fatRatio,
                onValueChange = {
                    onAction(NutrientGoalScreenAction.OnFatRatioEnter(it))
                },
                unit = stringResource(R.string.percent_fats)
            )
        }

        ActionButton(
            text = stringResource(R.string.next),
            onClick = {
                onAction(NutrientGoalScreenAction.OnNextClick)
            },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NutrientScreenPreview() {
    NutrientGoalScreen(
        onAction = {},
        state = NutrientGoalScreenState()
    )
}