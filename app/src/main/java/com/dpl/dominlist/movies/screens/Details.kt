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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dpl.dominlist.movies.components.movieItemsExample
import com.dpl.dominlist.movies.model.MovieItem
import com.dpl.dominlist.movies.viewmodel.MoviesHomeViewModel

@Composable
fun ShowDetailsScreen(
    navController: NavHostController? = null,
    movieId: Long? = null,
    viewModel: MoviesHomeViewModel = hiltViewModel()
) {
    val movie = viewModel.movieList.collectAsState().value
        .firstOrNull { movieItem -> movieItem.id == movieId }

    Details(navController = navController, movie = movie)
}


@Preview
@Composable
private fun ShowDetailsPreview() {
    Details(movie = movieItemsExample.first())
}

@Composable
private fun Details(
    navController: NavHostController? = null,
    movie: MovieItem?= null,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        DetailsTopBar(movie, navController)
        DetailsContent(movie)
    }
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DetailsTopBar(
    movie: MovieItem?,
    navController: NavHostController?
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
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack, "Back Icon",
                    modifier = Modifier.clickable {
                        navController?.popBackStack()
                    }
                )
            }
        }
    )
}

@Composable
fun DetailsContent(movie: MovieItem?) {
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
                lineHeight = 1.8.em,
                fontSize = 28.sp,
                text = movie?.description ?: "no info"
            )
        }

    }
}