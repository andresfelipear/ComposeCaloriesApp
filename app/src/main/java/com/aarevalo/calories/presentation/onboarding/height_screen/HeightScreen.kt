package com.aarevalo.calories.presentation.onboarding.height_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.aarevalo.calories.R
import com.aarevalo.calories.presentation.onboarding.components.ActionButton
import com.aarevalo.calories.presentation.onboarding.components.UnitTextField
import com.aarevalo.calories.ui.theme.CaloriesTheme
import com.aarevalo.calories.ui.theme.LocalSpacing

@Composable
fun HeightScreen(
    onNextClick: () -> Unit,
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
                text = stringResource(R.string.whats_your_height),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.padding(spacing.spaceMedium))

            UnitTextField(
                value = "180",
                onValueChange = {},
                unit = stringResource(R.string.cm)
            )
        }

        ActionButton(
            text = stringResource(R.string.next),
            onClick = { onNextClick() },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeightScreenPreview() {
    CaloriesTheme {
        HeightScreen(onNextClick = {})
    }
}