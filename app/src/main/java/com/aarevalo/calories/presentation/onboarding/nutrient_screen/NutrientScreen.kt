package com.aarevalo.calories.presentation.onboarding.nutrient_screen

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.aarevalo.calories.R
import com.aarevalo.calories.presentation.onboarding.components.ActionButton
import com.aarevalo.calories.presentation.onboarding.components.UnitTextField
import com.aarevalo.calories.ui.theme.LocalSpacing

@SuppressLint("UnrememberedMutableState")
@Composable
fun NutrientScreen(
    onNextClick: () -> Unit,
) {
    val spacing = LocalSpacing.current

    var carbs by mutableStateOf("40")
    var proteins by mutableStateOf("30")
    var fats by mutableStateOf("30")

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
                value = carbs,
                onValueChange = {},
                unit = stringResource(R.string.percent_carbs)
            )

            Spacer(modifier = Modifier.padding(spacing.spaceMedium))

            UnitTextField(
                value = proteins,
                onValueChange = {},
                unit = stringResource(R.string.percent_proteins)
            )

            Spacer(modifier = Modifier.padding(spacing.spaceMedium))

            UnitTextField(
                value = fats,
                onValueChange = {},
                unit = stringResource(R.string.percent_fats)
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
fun NutrientScreenPreview() {
    NutrientScreen(onNextClick = {})
}