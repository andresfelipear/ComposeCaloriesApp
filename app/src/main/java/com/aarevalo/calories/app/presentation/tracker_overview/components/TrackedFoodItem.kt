package com.aarevalo.calories.app.presentation.tracker_overview.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
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
import com.aarevalo.calories.app.domain.tracker.model.MealType
import com.aarevalo.calories.app.domain.tracker.model.TrackedFood
import com.aarevalo.calories.app.ui.theme.CaloriesTheme
import com.aarevalo.calories.app.ui.theme.LocalSpacing
import java.time.LocalDate

@Composable
fun TrackedFoodItem(
    trackedFood: TrackedFood, onDeleteItem: () -> Unit, modifier: Modifier = Modifier
) {
    val spacing = LocalSpacing.current
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .padding(spacing.spaceExtraSmall)
            .shadow(elevation = 1.dp, shape = RoundedCornerShape(5.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(end = spacing.spaceMedium)
            .height(100.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = trackedFood.imageUrl,
                placeholder = painterResource(id = R.drawable.ic_burger),
                error = painterResource(id = R.drawable.ic_burger)
            ),
            contentDescription = trackedFood.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(topStart = 5.dp, bottomStart = 5.dp))
        )
        Spacer(modifier = Modifier.width(spacing.spaceMedium))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = trackedFood.name,
                style = MaterialTheme.typography.bodyLarge,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
            )
            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
            Text(
                text = stringResource(
                    id = R.string.nutrient_info, trackedFood.amount, trackedFood.calories
                ), style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.width(spacing.spaceMedium))
        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
            Icon(imageVector = Icons.Default.Close,
                 contentDescription = stringResource(id = R.string.delete),
                 modifier = Modifier
                     .align(Alignment.End)
                     .clickable { onDeleteItem() })
            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
            Row(verticalAlignment = Alignment.CenterVertically) {
                NutrientInfo(
                    name = stringResource(id = R.string.carbs),
                    amount = trackedFood.carbs,
                    unit = stringResource(id = R.string.grams),
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.width(spacing.spaceSmall))
                NutrientInfo(
                    name = stringResource(id = R.string.protein),
                    amount = trackedFood.protein,
                    unit = stringResource(id = R.string.grams),
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.width(spacing.spaceSmall))
                NutrientInfo(
                    name = stringResource(id = R.string.fat),
                    amount = trackedFood.fat,
                    unit = stringResource(id = R.string.grams),
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrackedFoodItemPreview() {
    CaloriesTheme {
        TrackedFoodItem(trackedFood = TrackedFood(
            id = 1,
            name = "Burger",
            imageUrl = "https://www.themealdb.com/images/media/meals/58oia61564916529.jpg",
            amount = 1,
            calories = 300,
            carbs = 20,
            protein = 10,
            fat = 15,
            mealType = MealType.Breakfast,
            date = LocalDate.now(),
        ), onDeleteItem = {})
    }
}