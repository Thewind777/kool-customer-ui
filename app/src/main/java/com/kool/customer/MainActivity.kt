package com.kool.customer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kool.customer.ui.screens.*
import com.kool.customer.ui.theme.KoolTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoolTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "welcome",
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
                            onMealClick = { mealId ->
                                navController.navigate("item/$mealId")
                            },
                        )
                    }
                    composable("item/{id}") {
                        ItemDetailScreen(
                            onBack = { navController.popBackStack() },
                            onAddToCart = { navController.popBackStack() },
                        )
                    }
                }
            }
        }
    }
}
