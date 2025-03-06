package com.aarevalo.calories.app.presentation.search.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aarevalo.calories.app.ui.theme.CaloriesTheme
import com.aarevalo.calories.app.ui.theme.LocalSpacing

@Composable
fun UnitTextField(
    name: String,
    amount: String,
    unit: String,
    modifier: Modifier = Modifier,
    unitTextSize: TextUnit = 14.sp,
    nameTextStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    onValueChange: (String) -> Unit
){
    val spacing = LocalSpacing.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = modifier
        ) {
            BasicTextField(
                value = amount,
                onValueChange = onValueChange,
                singleLine = true,
                modifier = Modifier
                    .padding(spacing.spaceExtraSmall)
                    .width(width = 24.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = if(amount.isNotBlank()) {
                        ImeAction.Done
                    } else ImeAction.Default,
                    keyboardType = KeyboardType.Number
                ),

                keyboardActions = KeyboardActions(
                    onDone = {
                        defaultKeyboardAction(ImeAction.Done)
                    }
                ),
            )
            Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))

            Text(
                text = unit,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = unitTextSize,
                modifier = Modifier.alignBy(LastBaseline)
            )
        }
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
fun UnitTextFieldPreview() {
    CaloriesTheme {
        UnitTextField(
            name = "Carbs",
            amount = "300",
            unit = "g",
            onValueChange = {}
        )
    }
}