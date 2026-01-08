package com.dpl.dominlist.movies.network

import com.dpl.dominlist.movies.BuildConfig
import com.dpl.dominlist.movies.utlis.logDebug
import com.dpl.dominlist.movies.utlis.logError
import com.dpl.dominlist.movies.utlis.logInfo
import info.movito.themoviedbapi.TmdbApi
import info.movito.themoviedbapi.TmdbMovies
import info.movito.themoviedbapi.model.core.MovieResultsPage
import javax.inject.Singleton

@Singleton
class MoviesApi {

    // not injectable!!!
    private val tmDbApi by lazy { TmdbApi(BuildConfig.MOVIES_API_KEY) }

    fun getAllPages(): List<MovieResultsPage> {
        val resultList = ArrayList<MovieResultsPage>()
        tmDbApi.movies?.let { moviesDB ->
            var page = 1
            var totalPagesNumber = 0
            do {
                getPLMoviesPage(moviesDB, page)?.let {
                    it.page
                    totalPagesNumber = it.totalPages
                    resultList.add(it)
                    it.forEach { logDebug(msg = "title: ${it.title}") }
                    logInfo(msg = "getPLMoviesPage: page=$page of $totalPagesNumber added!")
                } ?: logError(msg = "getPLMoviesPage: page=$page of $totalPagesNumber is null")
                page++
            } while (page <= totalPagesNumber)
        }
        return resultList
    }

    fun getPLMoviesPage(movies : TmdbMovies, page: Int?): MovieResultsPage?  = movies.getNowPlayingMovies("pl", page, "PL")
}
