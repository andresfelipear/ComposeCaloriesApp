package com.aarevalo.calories.app.onboarding.age_screen

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
import com.aarevalo.calories.app.onboarding.components.ActionButton
import com.aarevalo.calories.app.onboarding.components.UnitTextField
import com.aarevalo.calories.app.ui.theme.CaloriesTheme
import com.aarevalo.calories.app.ui.theme.LocalSpacing
import com.aarevalo.calories.core.domain.util.UiEvent

@Composable
fun AgeScreenRoot(
    onNextClick: () -> Unit,
    viewModel: AgeViewModel = hiltViewModel(),
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
    AgeScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is AgeScreenAction.OnAgeEnter -> viewModel.onAction(action)
                is AgeScreenAction.OnNextClick -> viewModel.onAction(action)
            }
        }
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun AgeScreen(
    onAction: (AgeScreenAction) -> Unit,
    state: AgeScreenState
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = stringResource(R.string.whats_your_age),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.padding(spacing.spaceMedium))

            UnitTextField(
                value = state.age,
                onValueChange = {
                    onAction(AgeScreenAction.OnAgeEnter(it))
                },
                unit = stringResource(R.string.years)
            )
        }

        ActionButton(
            text = stringResource(R.string.next),
            onClick = {
                onAction(AgeScreenAction.OnNextClick)
            },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AgeScreenPreview() {
    CaloriesTheme {
        AgeScreen(
            state = AgeScreenState(),
            onAction = {}
        )
    }
}