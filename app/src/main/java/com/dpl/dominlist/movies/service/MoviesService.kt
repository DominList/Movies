package com.dpl.dominlist.movies.service

import com.dpl.dominlist.movies.data.MoviesDao
import com.dpl.dominlist.movies.model.Mapper.map
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
            async(Dispatchers.Default) {
                moviePages.flatMap { page -> extractPage(page) }
            }.await().let { movies ->
                moviesDao.insertAll(movies)
            }
        }


    private fun extractPage(page: MovieResultsPage): List<MovieItem> =
        page.map { movieDb: MovieDb -> map(movieDb) }.toList()

}
