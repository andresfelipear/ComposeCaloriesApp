package com.aarevalo.calories.app.presentation.search.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.aarevalo.calories.R
import com.aarevalo.calories.app.domain.tracker.model.TrackableFood
import com.aarevalo.calories.app.presentation.search.model.TrackableFoodUiState
import com.aarevalo.calories.app.presentation.tracker_overview.components.NutrientInfo
import com.aarevalo.calories.app.ui.theme.CaloriesTheme
import com.aarevalo.calories.app.ui.theme.LocalSpacing

@Composable
fun TrackableFoodItem(
    state: TrackableFoodUiState,
    onClick:() -> Unit,
    onAmountChange: (String) -> Unit,
    onTrack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val food = state.food
    val spacing = LocalSpacing.current
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .padding(spacing.spaceExtraSmall)
            .shadow(elevation = 1.dp, shape = RoundedCornerShape(5.dp))
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onClick() }
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
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(5.dp))
                )
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                Column(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = food.name,
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.width(spacing.spaceSmall))
                    if(food.caloriesPer100g == null){
                        Text(
                            text = stringResource(id = R.string.kcal_per_100g, 0),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    } else {
                        Text(
                            text = stringResource(id = R.string.kcal_per_100g, food.caloriesPer100g),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
            Row {
                NutrientInfo(
                    name = stringResource(id = R.string.carbs),
                    amount = food.carbsPer100g ?: 0,
                    unit = stringResource(id = R.string.grams),
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.width(spacing.spaceSmall))
                NutrientInfo(
                    name = stringResource(id = R.string.protein),
                    amount = food.proteinPer100g ?: 0,
                    unit = stringResource(id = R.string.grams),
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.width(spacing.spaceSmall))
                NutrientInfo(
                    name = stringResource(id = R.string.fat),
                    amount = food.fatPer100g ?: 0,
                    unit = stringResource(id = R.string.grams),
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.bodyMedium
                )
            }
        }
        AnimatedVisibility(
            visible = state.isExpanded
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing.spaceMedium),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrackableFoodItemPreview(){
    CaloriesTheme {
        TrackableFoodItem(
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
            onClick = {},
            onAmountChange = {},
            onTrack = {}
        )
    }
}