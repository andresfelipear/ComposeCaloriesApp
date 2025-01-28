package com.aarevalo.calories.app.presentation.tracker_overview.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun NutrientInfo(
    name: String,
    amount: Int,
    unit: String,
    modifier: Modifier = Modifier,
    amountTextSize: TextUnit = 20.sp,
    amountColor: Color = MaterialTheme.colorScheme.onBackground,
    unitTextSize: TextUnit = 14.sp,
    unitColor: Color = MaterialTheme.colorScheme.onBackground,
    nameTextStyle: TextStyle = MaterialTheme.typography.bodyMedium
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UnitDisplay(
            amount = amount,
            amountColor = amountColor,
            amountTextSize = amountTextSize,
            unit = unit,
            unitColor = unitColor,
            unitTextSize = unitTextSize
        )
        Text(
            text = name,
            color = MaterialTheme.colorScheme.onBackground,
            style = nameTextStyle,
            fontWeight = FontWeight.Light
        )
    }

}

@Preview(showBackground = true)
@Composable
fun NutrientInfoPreview() {
    NutrientInfo(
        name = "Carbs",
        amount = 100,
        unit = "g"
    )
}