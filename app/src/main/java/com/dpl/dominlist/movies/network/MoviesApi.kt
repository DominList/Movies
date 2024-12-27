package com.dpl.dominlist.movies.network

import android.util.Log
import com.dpl.dominlist.movies.BuildConfig
import info.movito.themoviedbapi.TmdbApi
import info.movito.themoviedbapi.TmdbMovies
import info.movito.themoviedbapi.model.core.MovieResultsPage
import javax.inject.Singleton

@Singleton
class MoviesApi {

    private val moviesApi: TmdbApi by lazy { TmdbApi(BuildConfig.MOVIES_API_KEY) }

    fun getAllPages(): List<MovieResultsPage> {

        val resultList = ArrayList<MovieResultsPage>()
        moviesApi.movies?.let { moviesDB ->
            var page = 1
            val totalPagesNumber = loadResultsPerPage(moviesDB, page)?.totalPages ?: 0
            while (page < totalPagesNumber) {
                page++
                loadResultsPerPage(moviesDB, page)?.let {
                    resultList.add(it)
                }
            }
        }
        return resultList
    }


    private fun loadResultsPerPage(movies: TmdbMovies, page: Int): MovieResultsPage? {
        Log.d("Movie","Loading page = $page")
        return movies.getNowPlayingMovies("pl", page, "PL")
    }

    val configUrl: String by lazy { moviesApi.configuration.secureBaseUrl}
}
