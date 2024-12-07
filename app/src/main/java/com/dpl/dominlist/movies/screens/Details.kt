package com.dpl.dominlist.movies.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dpl.dominlist.movies.model.MovieItem
import com.dpl.dominlist.movies.viewmodel.MoviesHomeViewModel

@Composable
fun Details(
    navController: NavHostController,
    movieId: Long,
    viewModel: MoviesHomeViewModel = hiltViewModel()
) {
    // todo this code is not matching any value at firs load
    //  which is caused by empty list of movies
    val movie: MovieItem? =
        viewModel.movieList.collectAsState().value.firstOrNull { movieItem -> movieItem.id == movieId }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        DetailsTopBar(movie, navController)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            val paddingValues = PaddingValues(10.dp)
            Row(modifier = Modifier.padding(paddingValues)) {
                Text(
                    fontSize = 14.sp,
                    text = movie?.description?: "no info")
            }

        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DetailsTopBar(
    movie: MovieItem?,
    navController: NavHostController
) {
    TopAppBar(
        title = {
            Text(text = movie?.title ?: "")
        },
        actions = {
            Icon(
                imageVector = Icons.Rounded.Star, contentDescription = "Rounded icon"
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.ArrowBack, "Back Icon",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }
                )
            }
        }
    )
}