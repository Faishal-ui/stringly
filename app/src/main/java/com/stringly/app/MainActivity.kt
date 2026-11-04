package com.stringly.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.*
import com.stringly.app.ui.screens.*
import com.stringly.app.ui.theme.PrimaryRed
import com.stringly.app.ui.theme.StringlyTheme

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Tuner : Screen("tuner", "Tuner", Icons.Default.GraphicEq)
    object Chords : Screen("chords", "Chords", Icons.Default.GridOn)
    object Metronome : Screen("metronome", "Tempo", Icons.Default.Speed)
    object Tabs : Screen("tabs", "Tabs", Icons.Default.QueueMusic)
    object JamSpot : Screen("jam-spot", "Jam Spot", Icons.Default.Groups)
    object Pedalboard : Screen("pedalboard", "Pedals", Icons.Default.Speaker)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StringlyTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Home.route

                val bottomNavItems = listOf(
                    Screen.Home,
                    Screen.Tuner,
                    Screen.Chords,
                    Screen.Tabs,
                    Screen.JamSpot
                )

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar(
                            containerColor = Color.White,
                            tonalElevation = 6.dp
                        ) {
                            bottomNavItems.forEach { screen ->
                                val selected = currentRoute == screen.route
                                NavigationBarItem(
                                    selected = selected,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.startDestinationId) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = screen.icon,
                                            contentDescription = screen.title
                                        )
                                    },
                                    label = {
                                        Text(
                                            text = screen.title,
                                            fontSize = 10.sp,
                                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
                                        )
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = PrimaryRed,
                                        selectedTextColor = PrimaryRed,
                                        indicatorColor = PrimaryRed.copy(alpha = 0.12f)
                                    )
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Home.route) {
                            HomeScreen(onNavigateTo = { route -> navController.navigate(route) })
                        }
                        composable(Screen.Tuner.route) {
                            TunerScreen()
                        }
                        composable(Screen.Chords.route) {
                            ChordsScreen()
                        }
                        composable(Screen.Metronome.route) {
                            MetronomeScreen()
                        }
                        composable(Screen.Tabs.route) {
                            TabsScreen()
                        }
                        composable(Screen.JamSpot.route) {
                            JamSpotScreen()
                        }
                        composable(Screen.Pedalboard.route) {
                            PedalboardScreen()
                        }
                    }
                }
            }
        }
    }
}
