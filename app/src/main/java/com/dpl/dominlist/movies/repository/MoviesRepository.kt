package com.dpl.dominlist.movies.repository

import android.util.Log
import com.dpl.dominlist.movies.model.MovieItem
import com.dpl.dominlist.movies.model.Movies
import com.dpl.dominlist.movies.network.MoviesApi
import info.movito.themoviedbapi.model.MovieDb
import info.movito.themoviedbapi.model.core.MovieResultsPage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val api: MoviesApi) {

    suspend fun getAllMovies(coroutineScope: CoroutineScope?): Movies {
        val resultMovies = Movies()
        coroutineScope?.launch {
            val deferredRequest = async(Dispatchers.IO) { api.getAllPages() }
            val moviePages = deferredRequest.await()

            val deferredExtraction = async(Dispatchers.Default) {
                val extractedMovies = Movies()
                if (moviePages.isNotEmpty()) {
                    api.getAllPages().forEach { page ->
                        extractPage(page).apply { extractedMovies.addAll(this) }
                    }
                } else {
                    Log.e("Movies", "Movie pages are empty!")
                }
                extractedMovies
            }
            resultMovies.addAll( deferredExtraction.await() )
        }
        return resultMovies
    }

    private suspend fun extractPage(page: MovieResultsPage): Movies {
        val movies = Movies()
        page.forEach { movieDb: MovieDb? ->
            movieDb?.let {
                MovieItem(
                    id = it.id,
                    title = it.title,
                    posterPath = it.posterPath,
                    homePage = it.homepage
                ).apply {
                    movies.add(this)
                    Log.d("Movie", this.toString())
                }

            }
        }
        return movies
    }
}
