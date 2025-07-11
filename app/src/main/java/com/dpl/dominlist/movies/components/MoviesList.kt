package com.dpl.dominlist.movies.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.dpl.dominlist.movies.model.MovieItem
import com.dpl.dominlist.movies.utlis.getPosterOrFallbackUrl

@Composable
@Preview
fun MoviesList(
    movieItems: State<List<MovieItem>> = remember { mutableStateOf(movieItemsExample) },
    onItemClick: (Long) -> Unit = {},
    onPosterClick: (Long) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(2.dp)
    ) {
        items(movieItems.value) { item ->
            Spacer(modifier = Modifier.fillMaxWidth().height(3.dp))
            SingleMovieCard(item, onItemClick, onPosterClick)
            Spacer(modifier = Modifier.fillMaxWidth().height(3.dp))
        }
    }
}

@Composable
private fun SingleMovieCard(
    item: MovieItem,
    onItemClick: (Long) -> Unit,
    onPosterClick: (Long) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onItemClick.invoke(item.id)
            },
    ) {
        Row {
            Surface(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(.4f)
                    .aspectRatio(.75f),
                shape = RoundedCornerShape(8.dp),
                shadowElevation = 5.dp,
            ) {
                Log.d("TAG", "SingleMovieCard: ${item.posterPath}")

                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current).data(data = getPosterOrFallbackUrl(item))
                            .apply(block = fun ImageRequest.Builder.() {
                                crossfade(true)
                                transformations(RoundedCornersTransformation())
                                scale(Scale.FILL)
                            }).build()
                    ),
                    contentDescription = "Movie image",
                    modifier = Modifier.clickable {
                        onPosterClick.invoke(item.id)
                    }
                )
            }

            Column {
                Text(
                    text = item.title ?: "no title",
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(5.dp)
                )
                Text(
                    text = item.posterPath ?: "no image url",
                    fontStyle = FontStyle.Italic,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(start = 5.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ImageTest() {

    val imagePath = "https://images-na.ssl-images-amazon.com/images/M/MV5BNzM2MDk3MTcyMV5BMl5BanBnXkFtZTcwNjg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg"

    Row {

        Surface(
            modifier = Modifier
                .padding(5.dp)
                .size(400.dp),
            shape = RoundedCornerShape(8.dp),
            shadowElevation = 5.dp,
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = imagePath)
                        .apply(block = fun ImageRequest.Builder.() {
                            crossfade(false)
                            transformations(RoundedCornersTransformation())
                            scale(Scale.FILL)
                        }).build()
                ),
                contentDescription = "Movie image"
            )
        }
    }
}


val movieItemsExample = listOf(
    MovieItem(
        id = 1,
        title = "example1",
        description = "That could be a very test description",
        posterPath = "https://images-na.ssl-images-amazon.com/images/M/MV5BNzM2MDk3MTcyMV5BMl5BanBnXkFtZTcwNjg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
    ),
    MovieItem(
        id = 1,
        title = "example2",
        description = "That could be a very test description",
        posterPath = "https://images-na.ssl-images-amazon.com/images/M/MV5BNzM2MDk3MTcyMV5BMl5BanBnXkFtZTcwNjg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
    ),
    MovieItem(
        id = 2,
        title = "example3",
        description = "That could be a very test description",
        posterPath = "https://images-na.ssl-images-amazon.com/images/M/MV5BNzM2MDk3MTcyMV5BMl5BanBnXkFtZTcwNjg0MTUzNA@@._V1_SX1777_CR0,0,1777,999_AL_.jpg",
    )
)