package com.aarevalo.calories.app.onboarding.activity_level_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.aarevalo.calories.R
import com.aarevalo.calories.app.onboarding.components.ActionButton
import com.aarevalo.calories.app.onboarding.components.SelectableButton
import com.aarevalo.calories.app.ui.theme.LocalSpacing
import com.aarevalo.calories.core.domain.model.ActivityLevel
import com.aarevalo.calories.core.domain.util.UiEvent

@Composable
fun ActivityLevelScreenRoot(
    onNextClick: () -> Unit,
    viewModel: ActivityLevelViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Success -> onNextClick()
                else -> Unit
            }
        }
    }

    ActivityLevelScreen(
        onAction = { action ->
            when(action) {
                is ActivityLevelScreenAction.OnActivityLevelSelect -> viewModel.onAction(action)
                is ActivityLevelScreenAction.OnNextClick -> viewModel.onAction(action)
            }
        },
        state = state
    )
}

@Composable
fun ActivityLevelScreen(
    onAction : (ActivityLevelScreenAction) -> Unit,
    state : ActivityLevelScreenState,
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
                text = stringResource(R.string.whats_your_activity_level),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleSmall,
            )

            Spacer(modifier = Modifier.padding(spacing.spaceMedium))

            Row {
                SelectableButton(
                    text = stringResource(R.string.low),
                    isSelected = state.selectedActivityLevel is ActivityLevel.Low,
                    color = MaterialTheme.colorScheme.primary,
                    selectedTextColor = Color.White,
                    onClick = {
                        onAction(ActivityLevelScreenAction.OnActivityLevelSelect(ActivityLevel.Low))
                    },
                    textStyle = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Normal
                    )
                )

                Spacer(modifier = Modifier.padding(spacing.spaceMedium))

                SelectableButton(
                    text = stringResource(R.string.medium),
                    isSelected = state.selectedActivityLevel is ActivityLevel.Medium,
                    color = MaterialTheme.colorScheme.primary,
                    selectedTextColor = Color.White,
                    onClick = {
                        onAction(ActivityLevelScreenAction.OnActivityLevelSelect(ActivityLevel.Medium))
                    },
                    textStyle = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Normal
                    )
                )

                Spacer(modifier = Modifier.padding(spacing.spaceMedium))

                SelectableButton(
                    text = stringResource(R.string.high),
                    isSelected = state.selectedActivityLevel is ActivityLevel.High,
                    color = MaterialTheme.colorScheme.primary,
                    selectedTextColor = Color.White,
                    onClick = {
                        onAction(ActivityLevelScreenAction.OnActivityLevelSelect(ActivityLevel.High))
                    },
                    textStyle = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Normal
                    )
                )

                Spacer(modifier = Modifier.padding(spacing.spaceMedium))
            }

        }

        ActionButton(
            text = stringResource(R.string.next),
            onClick = {
                onAction(ActivityLevelScreenAction.OnNextClick)
            },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ActivityLevelScreenPreview() {
    ActivityLevelScreen(
        onAction = {},
        state = ActivityLevelScreenState()
    )
}