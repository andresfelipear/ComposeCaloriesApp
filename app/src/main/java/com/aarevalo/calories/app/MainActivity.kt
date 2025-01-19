package com.aarevalo.calories.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.aarevalo.calories.core.navigation.NavigationRoot
import com.aarevalo.calories.app.ui.theme.CaloriesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaloriesTheme {
                val navController = rememberNavController()
                NavigationRoot(
                    navHostController = navController
                )
            }
        }
    }
}