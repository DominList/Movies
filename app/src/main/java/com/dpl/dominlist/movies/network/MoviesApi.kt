package com.dpl.dominlist.movies.network

import android.util.Log
import com.dpl.dominlist.movies.BuildConfig
import com.dpl.dominlist.movies.utlis.getTAG
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
                    it.forEach { Log.d(getTAG(), "title: ${it.title}") }
                    Log.i(getTAG(), "getPLMoviesPage: page=$page of $totalPagesNumber added!")
                } ?: Log.e(getTAG(), "getPLMoviesPage: page=$page of $totalPagesNumber is null")
                page++
            } while (page <= totalPagesNumber)
        }
        return resultList
    }

    fun getPLMoviesPage(movies : TmdbMovies, page: Int?): MovieResultsPage?  = movies.getNowPlayingMovies("pl", page, "PL")
}
