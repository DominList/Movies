package com.dpl.dominlist.movies.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.dpl.dominlist.movies.R
import com.dpl.dominlist.movies.components.MoviesList
import com.dpl.dominlist.movies.model.MovieItem
import com.dpl.dominlist.movies.navigation.MovieScreens
import com.dpl.dominlist.movies.viewmodel.MoviesHomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeListScreen(
    navController: NavController,
    viewModel: MoviesHomeViewModel = hiltViewModel()
) {

    val movieItems : State<List<MovieItem>> = viewModel.movieList.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.title))
            },
            actions = {
                Icon(
                    imageVector = Icons.Rounded.Notifications, contentDescription = "Rounded icon"
                )
            },
            colors =  TopAppBarDefaults.centerAlignedTopAppBarColors()
        )
        // test
        MoviesList(
            movieItems = remember { movieItems },
            onItemClick = {
                navController.navigate(route = MovieScreens.Detail.name+"/$it")
            },
            onPosterClick = {
                navController.navigate(route = MovieScreens.Poster.name+"/$it")
            }
        )
    }
}