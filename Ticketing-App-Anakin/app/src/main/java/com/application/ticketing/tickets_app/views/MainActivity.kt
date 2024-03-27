package com.application.ticketing.tickets_app.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.application.ticketing.tickets_app.data.viewmodel.TicketsViewModel
import com.application.ticketing.tickets_app.navigation.Screen
import com.application.ticketing.tickets_app.ui.theme.TicketsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            TicketsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val ticketsViewModel = TicketsViewModel(application)
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route
                    ) {
                        composable(route = Screen.HomeScreen.route) {
                            HomeScreen(navController = navController)
                        }
                        composable(route = Screen.AddTicketScreen.route) {
                            AddTicketScreen(ticketsViewModel = ticketsViewModel)
                        }
                        composable(route = Screen.AllTicketsScreen.route) {
                            AllTicketsScreen(
                                navController = navController,
                                ticketsViewModel = ticketsViewModel
                            )
                        }
                        composable(
                            route = Screen.TicketDetailsScreen.route + "/{ticket_id}",
                            arguments = listOf(
                                navArgument("ticket_id") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                    nullable = false
                                }
                            )
                        ) {
                            val id = it.arguments?.getInt("ticket_id") ?: -1
                            TicketDetailScreen(id, ticketsViewModel = ticketsViewModel, navController)
                        }
                    }
                }
            }
        }
    }
}