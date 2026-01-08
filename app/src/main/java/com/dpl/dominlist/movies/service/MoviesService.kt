package com.dpl.dominlist.movies.service

import com.dpl.dominlist.movies.model.Mapper.map
import com.dpl.dominlist.movies.model.MovieItem
import com.dpl.dominlist.movies.network.MoviesApi
import com.dpl.dominlist.movies.repository.MoviesRepository
import com.dpl.dominlist.movies.repository.PreferencesService
import com.dpl.dominlist.movies.utlis.logDebug
import info.movito.themoviedbapi.model.MovieDb
import info.movito.themoviedbapi.model.core.MovieResultsPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesService @Inject constructor(
    private val api: MoviesApi,
    private val moviesDao: MoviesRepository,
    private val preferencesService: PreferencesService
) {

    suspend fun fetchData() {
        if (preferencesService.shouldUpdateDB().not()) {
            logDebug(msg = "fetchData: update not needed")
            return
        }

        withContext(Dispatchers.IO) {
            api.getAllPages()
                .flatMap { page -> extractPage(page) }
                .let { movies ->
                    logDebug(msg = "movies update: ${movies.size}")
                    moviesDao.addOrUpdateMovies(movies)
                    preferencesService.markDbUpdated()
                }
        }
    }


    private fun extractPage(page: MovieResultsPage): List<MovieItem> =
        page.mapNotNull { movieDb: MovieDb -> map(movieDb) }.toList()

}
