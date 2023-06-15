package com.dpl.dominlist.movies.repository

import android.util.Log
import com.dpl.dominlist.movies.data.DataWrapper
import com.dpl.dominlist.movies.model.MovieItem
import com.dpl.dominlist.movies.model.Movies
import com.dpl.dominlist.movies.network.MoviesApi
import info.movito.themoviedbapi.model.MovieDb
import info.movito.themoviedbapi.model.core.MovieResultsPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val api: MoviesApi) {


    private val dataWrapper = DataWrapper(Movies())
    suspend fun getAllMovies(): DataWrapper<Movies> =
        withContext(Dispatchers.IO) {

            val deferredRequest = async { api.getAllPages() }
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
                extractedMovies.sortBy { it.title }
                extractedMovies
            }
            dataWrapper.wrappedData = deferredExtraction.await()
            dataWrapper
        }



    private fun extractPage(page: MovieResultsPage): Movies {
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
                }
            }
        }
        return movies
    }
}
