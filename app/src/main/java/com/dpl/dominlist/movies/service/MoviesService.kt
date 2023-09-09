package com.dpl.dominlist.movies.service

import android.util.Log
import com.dpl.dominlist.movies.data.MoviesDao
import com.dpl.dominlist.movies.model.MovieItem
import com.dpl.dominlist.movies.network.MoviesApi
import info.movito.themoviedbapi.model.MovieDb
import info.movito.themoviedbapi.model.core.MovieResultsPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesService @Inject constructor(
    private val api: MoviesApi,
    private val moviesDao: MoviesDao
) {

    suspend fun fetchData() =
        withContext(Dispatchers.IO) {
            val deferredRequest = async { api.getAllPages() }
            val moviePages = deferredRequest.await()

            val deferredExtraction = async(Dispatchers.Default) {
                val extractedMovies = ArrayList<MovieItem>()
                if (moviePages.isNotEmpty()) {
                    api.getAllPages().forEach { page ->
                        extractPage(page).apply { extractedMovies.addAll(this) }
                    }
                } else {
                    Log.e(this.javaClass.simpleName, "Movie pages are empty!")
                }
                extractedMovies.sortBy { it.title }
                extractedMovies
            }
            deferredExtraction.await().let { movies ->
                moviesDao.insertAll(movies)
            }
        }


    private fun extractPage(page: MovieResultsPage): List<MovieItem> {
        val movies = ArrayList<MovieItem>()
        page.forEach { movieDb: MovieDb? ->
            movieDb?.let {
                MovieItem(
                    id = it.id.toString(),
                    title = it.title,
                    description = it.overview,
//                    alternativeTitles = it.alternativeTitles,
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
