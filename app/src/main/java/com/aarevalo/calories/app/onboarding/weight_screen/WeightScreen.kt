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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.aarevalo.calories.R
import com.aarevalo.calories.app.onboarding.components.ActionButton
import com.aarevalo.calories.app.onboarding.components.UnitTextField
import com.aarevalo.calories.app.ui.theme.LocalSpacing

@SuppressLint("UnrememberedMutableState")
@Composable
fun WeightScreen(
    onNextClick: () -> Unit,
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
                value = weight,
                onValueChange = {},
                unit = stringResource(R.string.kg)
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
fun WeightScreenPreview() {
    WeightScreen(onNextClick = {})
}