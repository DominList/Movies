package com.dpl.dominlist.movies.screens

//noinspection SuspiciousImport

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.dpl.dominlist.movies.components.movieItemsExample
import com.dpl.dominlist.movies.utlis.getOriginalPosterOrFallbackUrl
import com.dpl.dominlist.movies.viewmodel.MoviesHomeViewModel

@Composable
fun ShowPosterScreen(
    movieId: Long? = null,
    viewModel: MoviesHomeViewModel = hiltViewModel()
) {
    val movie = viewModel.movieList.collectAsState().value
        .firstOrNull { movieItem -> movieItem.id == movieId }

    Poster(url = getOriginalPosterOrFallbackUrl(movie))
}


@Preview
@Composable
private fun ShowPosterPreview() {
    Poster(url = movieItemsExample.last().posterPath)
}

@Composable
private fun Poster(
    url: String? = null,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        PosterContent(url)
    }
}


@Composable
private fun PosterContent(url: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Todo move to the center
//        CircularProgressIndicator(
//            modifier = Modifier.wrapContentSize()
//        )

        Image(
            modifier = Modifier.fillMaxSize().padding(0.dp),
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = url)
                    .apply(block = fun ImageRequest.Builder.() {
                        crossfade(true)
                        transformations(RoundedCornersTransformation())
                        scale(Scale.FIT)
                    }).build()
            ),
            contentDescription = "Movie image"
        )
    }
}