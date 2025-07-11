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
import com.dpl.dominlist.movies.screens.ShowPosterScreen


@Preview
@Composable
fun MovieNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MovieScreens.Movies.name) {

        composable(route = MovieScreens.Movies.name) {
            HomeListScreen(navController = navController)
        }

        composable(
            route = getRoute(MovieScreens.Detail, MOVIE_ARG),
            arguments = getNavArgumentList(MOVIE_ARG)
        ) { backStackEntry: NavBackStackEntry ->
            ShowDetailsScreen(
                navController = navController,
                movieId = backStackEntry.arguments?.getLong(MOVIE_ARG)!!
            )
        }

        composable(
            route = getRoute(MovieScreens.Poster, MOVIE_ARG),
            arguments = getNavArgumentList(MOVIE_ARG)
        ) { backStackEntry: NavBackStackEntry ->
            ShowPosterScreen(
                movieId = backStackEntry.arguments?.getLong(MOVIE_ARG)!!
            )
        }
    }
}

const val MOVIE_ARG = "movie"

fun getRoute(screen: MovieScreens, pathArg: String) = "${screen.name}/{$pathArg}"
fun getNavArgumentList(pathArg: String) = listOf(navArgument(name = pathArg) { type = NavType.LongType })
