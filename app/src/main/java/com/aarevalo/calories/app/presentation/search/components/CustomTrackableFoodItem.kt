package com.aarevalo.calories.app.presentation.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.aarevalo.calories.R
import com.aarevalo.calories.app.domain.tracker.model.TrackableFood
import com.aarevalo.calories.app.presentation.search.model.TrackableFoodUiState
import com.aarevalo.calories.app.ui.theme.CaloriesTheme
import com.aarevalo.calories.app.ui.theme.LocalSpacing

@Composable
fun CustomTrackableFoodItem(
    state: TrackableFoodUiState,
    onAttributeChange: (String, String) -> Unit,
    onTrack: () -> Unit,
    modifier: Modifier = Modifier
){
    val food = state.food
    val spacing = LocalSpacing.current
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .padding(spacing.spaceSmall)
            .shadow(elevation = 1.dp, shape = RoundedCornerShape(5.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(end = spacing.spaceMedium),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = food.imageUrl,
                        error = painterResource(id = R.drawable.ic_burger),
                        placeholder = painterResource(id = R.drawable.ic_burger)
                    ),
                    contentDescription = food.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(5.dp))
                )
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                Column(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    BasicTextField(
                        value = food.name,
                        onValueChange = {
                            onAttributeChange("name",it) },
                        singleLine = true,
                        modifier = Modifier
                            .padding(spacing.spaceExtraSmall)
                            .semantics {
                                contentDescription = "Name"
                            }
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceSmall))
                    Row(
                        modifier = modifier
                    ) {
                        BasicTextField(
                            value = (food.caloriesPer100g?:"").toString(),
                            onValueChange = {onAttributeChange("calories",it)},
                            singleLine = true,
                            modifier = Modifier
                                .padding(spacing.spaceExtraSmall)
                                .width(width = 23.dp)
                                .alignBy(LastBaseline)
                        )
                        Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
                        Text(
                            text = stringResource(R.string.kcal_per_100g_label),
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.alignBy(LastBaseline)
                        )
                    }
                }
            }
            Row {
                UnitTextField(
                    name = stringResource(id = R.string.carbs),
                    amount = (food.carbsPer100g ?: "").toString(),
                    unit = stringResource(id = R.string.grams),
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.bodyMedium,
                    onValueChange = {
                        onAttributeChange("carbs",it)
                    }
                )
                Spacer(modifier = Modifier.width(spacing.spaceSmall))
                UnitTextField(
                    name = stringResource(id = R.string.protein),
                    amount = (food.proteinPer100g ?: "").toString(),
                    unit = stringResource(id = R.string.grams),
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.bodyMedium,
                    onValueChange = {
                        onAttributeChange("protein",it)
                    }
                )
                Spacer(modifier = Modifier.width(spacing.spaceSmall))
                UnitTextField(
                    name = stringResource(id = R.string.fat),
                    amount = (food.fatPer100g ?: "").toString(),
                    unit = stringResource(id = R.string.grams),
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.bodyMedium,
                    onValueChange = {
                        onAttributeChange("fat",it)
                    }
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.spaceMedium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                BasicTextField(
                    value = state.amount,
                    onValueChange = {
                        onAttributeChange("amount",it)
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = if(state.amount.isNotBlank()) {
                            ImeAction.Done
                        } else ImeAction.Default,
                        keyboardType = KeyboardType.Number
                    ),

                    keyboardActions = KeyboardActions(
                        onDone = {
                            defaultKeyboardAction(ImeAction.Done)
                        }
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .border(
                            shape = RoundedCornerShape(5.dp),
                            width = 0.5.dp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        .alignBy(LastBaseline)
                        .padding(spacing.spaceMedium)
                        .semantics {
                            contentDescription = "Amount"
                        }
                )
                Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
                Text(
                    text = stringResource(id = R.string.grams),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.alignBy(LastBaseline)
                )
            }
            IconButton(
                onClick = onTrack,
                enabled = state.amount.isNotBlank() && state.food.name.isNotBlank()
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(id = R.string.track)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomTrackableFoodItemPreview(){
    CaloriesTheme {
        CustomTrackableFoodItem(
            state = TrackableFoodUiState(
                food = TrackableFood(
                    name = "Burger",
                    imageUrl = "",
                    caloriesPer100g = 100,
                    carbsPer100g = 10,
                    proteinPer100g = 100,
                    fatPer100g = 100
                ),
                isExpanded = true,
            ),
            onTrack = {},
            onAttributeChange = {
                    _, _ ->
            }
        )
    }
}
