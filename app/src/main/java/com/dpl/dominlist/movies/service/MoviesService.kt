package com.dpl.dominlist.movies.service

import android.util.Log
import com.dpl.dominlist.movies.data.MoviesDao
import com.dpl.dominlist.movies.model.MovieItem
import com.dpl.dominlist.movies.network.MoviesApi
import info.movito.themoviedbapi.model.MovieDb
import info.movito.themoviedbapi.model.core.MovieResultsPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
import java.util.Date
import javax.inject.Inject

class MoviesService @Inject constructor(
    private val api: MoviesApi,
    private val moviesDao: MoviesDao
) {

    suspend fun fetchData() =
        withContext(Dispatchers.IO) {
            val moviePages = api.getAllPages()
            val extractedMovies = ArrayList<MovieItem>()
            if (moviePages.isNotEmpty()) {
                val deletedNumber = moviesDao.deleteAll()
                Log.d(javaClass.simpleName, "Deleted entries number= $deletedNumber")
                moviePages.forEach { page ->
                    extractPage(page).apply { extractedMovies.addAll(this) }
                }
            } else {
                Log.e(this.javaClass.simpleName, "Movie pages are empty!")
            }
            extractedMovies.sortBy { it.title }
            extractedMovies.let { movies ->
                moviesDao.insertAll(movies)
            }
        }


    private fun extractPage(page: MovieResultsPage): List<MovieItem> {
        val movies = ArrayList<MovieItem>()
        page.forEach { movieDb: MovieDb? ->
            movieDb?.let {
                MovieItem(
                    id = it.id.toLong(),
                    title = it.title,
                    description = it.overview,
                    posterPath = createPosterPathUrl(it),
                    homePage = it.homepage,
                    isAdult = it.isAdult,
                    runtime = "",
                ).apply {
                    movies.add(this)
                }
            }
        }
        return movies
    }

    private val baseUrl: String by lazy { api.configUrl }

    private fun createPosterPathUrl(movieDb: MovieDb): String {
        val posterFullPath = "${baseUrl}w200${movieDb.posterPath}"
        Log.d(javaClass.simpleName, "ImageUrl = $posterFullPath title = '${movieDb.title}'")
        return posterFullPath
    }
}
