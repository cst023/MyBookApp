package com.example.mybookapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mybookapp.screens.DetailScreen
import com.example.mybookapp.screens.HomeScreen
import com.example.mybookapp.BookViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BookNavigation() {
    val navController = rememberNavController()

    // Create the ViewModel at the NavHost level to share it across screens
    val bookViewModel: BookViewModel = viewModel() // or hiltViewModel() if using Hilt

    NavHost(
        navController = navController,
        startDestination = BookScreens.HomeScreen.name
    ) {
        composable(BookScreens.HomeScreen.name) {
            HomeScreen(navController = navController, bookViewModel = bookViewModel)
        }

        composable(
            route = BookScreens.DetailScreen.name + "/{book}",
            arguments = listOf(navArgument(name = "book") { type = NavType.StringType })
        ) { backStackEntry ->
            DetailScreen(
                navController = navController,
                bookId = backStackEntry.arguments?.getString("book"),
                bookViewModel = bookViewModel // Pass the same ViewModel
            )
        }
    }
}
