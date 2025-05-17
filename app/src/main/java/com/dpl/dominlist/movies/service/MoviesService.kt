package com.dpl.dominlist.movies.service

import android.util.Log
import com.dpl.dominlist.movies.data.MoviesDao
import com.dpl.dominlist.movies.model.Mapper.map
import com.dpl.dominlist.movies.model.MovieItem
import com.dpl.dominlist.movies.network.MoviesApi
import com.dpl.dominlist.movies.utlis.getTAG
import info.movito.themoviedbapi.model.MovieDb
import info.movito.themoviedbapi.model.core.MovieResultsPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesService @Inject constructor(
    private val api: MoviesApi,
    private val moviesDao: MoviesDao
) {

    suspend fun fetchData() =
        withContext(Dispatchers.IO) {
            api.getAllPages()
                .flatMap { page -> extractPage(page) }
                .let { movies ->
                    Log.d(getTAG(), "movies update: ${movies.size}")
                    moviesDao.insertAll(movies)
                }
        }


    private fun extractPage(page: MovieResultsPage): List<MovieItem> =
        page.mapNotNull { movieDb: MovieDb -> map(movieDb) }.toList()

}
