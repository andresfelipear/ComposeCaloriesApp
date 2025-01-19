package com.aarevalo.calories.core.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aarevalo.calories.app.onboarding.activity_level_screen.ActivityLevelScreenRoot
import com.aarevalo.calories.app.onboarding.age_screen.AgeScreenRoot
import com.aarevalo.calories.app.onboarding.gender_screen.GenderScreenRoot
import com.aarevalo.calories.app.onboarding.goal_screen.GoalScreenRoot
import com.aarevalo.calories.app.onboarding.height_screen.HeightScreenRoot
import com.aarevalo.calories.app.onboarding.nutrient_screen.NutrientGoalScreenRoot
import com.aarevalo.calories.app.onboarding.tracker_overview.TrackerOverviewScreenRoot
import com.aarevalo.calories.app.onboarding.weight_screen.WeightScreenRoot
import com.aarevalo.calories.app.onboarding.welcome.WelcomeScreen

@Composable
fun NavigationRoot(
    shouldShowOnboarding: Boolean,
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState
){
    println("Should Show onboarding: $shouldShowOnboarding")
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navHostController,
            startDestination = if(shouldShowOnboarding) WelcomeScreenRoute else TrackerOverviewScreenRoute
        ) {
            composable<WelcomeScreenRoute>(){
                WelcomeScreen {
                    navHostController.navigate(GenderScreenRoute)
                }
            }
            composable<GenderScreenRoute>(){
                GenderScreenRoot(
                    onNextClick = {
                        navHostController.navigate(AgeScreenRoute)
                    },
                )
            }
            composable<AgeScreenRoute>(){
                AgeScreenRoot(
                    onNextClick = {navHostController.navigate(HeightScreenRoute)},
                    snackbarHostState = snackbarHostState
                )
            }
            composable<HeightScreenRoute>(){
                HeightScreenRoot(
                    onNextClick = {navHostController.navigate(WeightScreenRoute)},
                    snackbarHostState = snackbarHostState
                )
            }
            composable<WeightScreenRoute>(){
                WeightScreenRoot(
                    onNextClick = {navHostController.navigate(ActivityLevelScreenRoute)},
                    snackbarHostState = snackbarHostState
                )
            }
            composable<ActivityLevelScreenRoute>(){
                ActivityLevelScreenRoot(
                    onNextClick = {navHostController.navigate(GoalScreenRoute)},
                )
            }
            composable<GoalScreenRoute>(){
                GoalScreenRoot(
                    onNextClick = {navHostController.navigate(NutrientGoalScreenRoute)},
                )
            }
            composable<NutrientGoalScreenRoute>(){
                NutrientGoalScreenRoot(
                    onNextClick = {navHostController.navigate(TrackerOverviewScreenRoute)},
                    snackbarHostState = snackbarHostState
                )
            }
            composable<TrackerOverviewScreenRoute>(){
                TrackerOverviewScreenRoot()
            }
        }
    }
}