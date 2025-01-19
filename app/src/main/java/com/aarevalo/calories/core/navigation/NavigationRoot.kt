package com.aarevalo.calories.core.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aarevalo.calories.app.onboarding.activity_level_screen.ActivityLevelScreen
import com.aarevalo.calories.app.onboarding.age_screen.AgeScreen
import com.aarevalo.calories.app.onboarding.gender_screen.GenderScreenRoot
import com.aarevalo.calories.app.onboarding.gender_screen.GenderViewModel
import com.aarevalo.calories.app.onboarding.goal_screen.GoalScreen
import com.aarevalo.calories.app.onboarding.height_screen.HeightScreen
import com.aarevalo.calories.app.onboarding.nutrient_screen.NutrientGoalScreen
import com.aarevalo.calories.app.onboarding.tracker_overview.TrackerOverviewScreen
import com.aarevalo.calories.app.onboarding.weight_screen.WeightScreen
import com.aarevalo.calories.app.onboarding.welcome.WelcomeScreen

@Composable
fun NavigationRoot(
    navHostController: NavHostController,
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
                AgeScreen {
                    navHostController.navigate(HeightScreenRoute)
                }
            }
            composable<HeightScreenRoute>(){
                HeightScreen {
                    navHostController.navigate(WeightScreenRoute)
                }
            }
            composable<WeightScreenRoute>(){
                WeightScreen {
                    navHostController.navigate(ActivityLevelScreenRoute)
                }
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