package com.kool.customer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kool.customer.data.model.MenuItemData
import com.kool.customer.ui.screens.*
import com.kool.customer.ui.theme.KoolTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoolTheme {
                val navController = rememberNavController()
                // Debug-only: lets a QA build open a screen directly, e.g.
                //   adb shell am start -n com.kool.customer/.MainActivity --es start item/0
                val startRoute = if (BuildConfig.DEBUG) {
                    intent?.getStringExtra("start") ?: "welcome"
                } else {
                    "welcome"
                }
                var selectedMeal by remember { mutableStateOf<MenuItemData?>(null) }
                NavHost(
                    navController = navController,
                    startDestination = startRoute,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    composable("welcome") {
                        WelcomeScreen(
                            onStart = { navController.navigate("discover") },
                            onLogin = { navController.navigate("discover") },
                        )
                    }
                    composable("discover") {
                        DiscoverScreen(
                            onRestaurantClick = { id ->
                                navController.navigate("restaurant/$id")
                            },
                        )
                    }
                    composable("restaurant/{id}") { backStack ->
                        val id = backStack.arguments?.getString("id")?.toIntOrNull() ?: 1
                        RestaurantMenuScreen(
                            restaurantId = id,
                            onBack = { navController.popBackStack() },
                            onMealClick = { meal ->
                                selectedMeal = meal
                                navController.navigate("item/${meal.id ?: "0"}")
                            },
                        )
                    }
                    composable("item/{id}") {
                        ItemDetailScreen(
                            onBack = { navController.popBackStack() },
                            onAddToCart = { navController.popBackStack() },
                            meal = selectedMeal,
                        )
                    }
                }
            }
        }
    }
}
