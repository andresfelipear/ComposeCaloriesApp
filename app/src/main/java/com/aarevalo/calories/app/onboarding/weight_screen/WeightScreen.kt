package com.aarevalo.calories.app.onboarding.weight_screen

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.aarevalo.calories.R
import com.aarevalo.calories.app.onboarding.components.ActionButton
import com.aarevalo.calories.app.onboarding.components.UnitTextField
import com.aarevalo.calories.app.ui.theme.LocalSpacing
import com.aarevalo.calories.core.domain.util.UiEvent

@Composable
fun WeightScreenRoot(
    onNextClick: () -> Unit,
    viewModel: WeightViewModel,
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

    WeightScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is WeightScreenAction.OnWeightEnter -> viewModel.onAction(action)
                is WeightScreenAction.OnNextClick -> viewModel.onAction(action)
            }
        }
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun WeightScreen(
    onAction : (WeightScreenAction) -> Unit,
    state : WeightScreenState
) {
    val space = LocalSpacing.current
    val weight by mutableStateOf("180")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(space.spaceLarge)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.whats_your_weight),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.padding(space.spaceMedium))

            UnitTextField(
                value = state.weight,
                onValueChange = {
                    onAction(WeightScreenAction.OnWeightEnter(it))
                },
                unit = stringResource(R.string.kg)
            )
        }

        ActionButton(
            text = stringResource(R.string.next),
            onClick = {
                onAction(WeightScreenAction.OnNextClick)
            },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WeightScreenPreview() {
    WeightScreen(
        state = WeightScreenState(),
        onAction = {}
    )
}