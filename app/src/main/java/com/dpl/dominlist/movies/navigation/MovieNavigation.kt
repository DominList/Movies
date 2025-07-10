package com.dpl.dominlist.movies.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dpl.dominlist.movies.screens.HomeListScreen
import com.dpl.dominlist.movies.screens.ShowDetailsScreen


@Preview
@Composable
fun MovieNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MovieScreens.Movies.name) {

        composable(route = MovieScreens.Movies.name) {
            HomeListScreen(navController = navController)
        }

        composable(
            route = MovieScreens.Detail.name + "/{movie}",
            arguments = listOf(navArgument(name = "movie") { type = NavType.LongType })
        ) { backStackEntry: NavBackStackEntry ->
            ShowDetailsScreen(
                navController = navController,
                backStackEntry.arguments?.getLong("movie")!!
            )
        }
    }
}
