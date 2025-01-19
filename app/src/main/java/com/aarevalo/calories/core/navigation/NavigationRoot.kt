package com.aarevalo.calories.core.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aarevalo.calories.app.onboarding.activity_level_screen.ActivityLevelScreen
import com.aarevalo.calories.app.onboarding.age_screen.AgeScreenRoot
import com.aarevalo.calories.app.onboarding.age_screen.AgeViewModel
import com.aarevalo.calories.app.onboarding.gender_screen.GenderScreenRoot
import com.aarevalo.calories.app.onboarding.gender_screen.GenderViewModel
import com.aarevalo.calories.app.onboarding.goal_screen.GoalScreen
import com.aarevalo.calories.app.onboarding.height_screen.HeightScreenRoot
import com.aarevalo.calories.app.onboarding.height_screen.HeightViewModel
import com.aarevalo.calories.app.onboarding.nutrient_screen.NutrientGoalScreen
import com.aarevalo.calories.app.onboarding.tracker_overview.TrackerOverviewScreen
import com.aarevalo.calories.app.onboarding.weight_screen.WeightScreenRoot
import com.aarevalo.calories.app.onboarding.weight_screen.WeightViewModel
import com.aarevalo.calories.app.onboarding.welcome.WelcomeScreen

@Composable
fun NavigationRoot(
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState
){
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navHostController,
            startDestination = WelcomeScreenRoute
        ) {
            composable<WelcomeScreenRoute>(){
                WelcomeScreen {
                    navHostController.navigate(GenderScreenRoute)
                }
            }
            composable<GenderScreenRoute>(){
                val viewModel = hiltViewModel<GenderViewModel>()
                GenderScreenRoot(
                    onNextClick = {
                        navHostController.navigate(AgeScreenRoute)
                    },
                    viewModel = viewModel
                )
            }
            composable<AgeScreenRoute>(){
                val viewModel = hiltViewModel<AgeViewModel>()
                AgeScreenRoot(
                    onNextClick = {navHostController.navigate(HeightScreenRoute)},
                    viewModel = viewModel,
                    snackbarHostState = snackbarHostState
                )
            }
            composable<HeightScreenRoute>(){
                val viewModel = hiltViewModel<HeightViewModel>()
                HeightScreenRoot(
                    onNextClick = {navHostController.navigate(WeightScreenRoute)},
                    viewModel = viewModel,
                    snackbarHostState = snackbarHostState
                )
            }
            composable<WeightScreenRoute>(){
                val viewModel = hiltViewModel<WeightViewModel>()
                WeightScreenRoot(
                    onNextClick = {navHostController.navigate(ActivityLevelScreenRoute)},
                    viewModel = viewModel,
                    snackbarHostState = snackbarHostState
                )
            }
            composable<ActivityLevelScreenRoute>(){
                ActivityLevelScreen {
                    navHostController.navigate(GoalScreenRoute)
                }
            }
            composable<GoalScreenRoute>(){
                GoalScreen {
                    navHostController.navigate(NutrientGoalScreenRoute)
                }
            }
            composable<NutrientGoalScreenRoute>(){
                NutrientGoalScreen {
                    navHostController.navigate(TrackerOverviewScreenRoute)
                }
            }
            composable<TrackerOverviewScreenRoute>(){
                TrackerOverviewScreen()
            }
        }
    }
}