package com.dpl.dominlist.movies.network

import android.util.Log
import com.dpl.dominlist.movies.BuildConfig
import com.dpl.dominlist.movies.utlis.getTAG
import com.dpl.dominlist.movies.utlis.logD
import com.dpl.dominlist.movies.utlis.logE
import com.dpl.dominlist.movies.utlis.logI
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
                    it.forEach { logD("title: ${it.title}") }
                    logI("getPLMoviesPage: page=$page of $totalPagesNumber added!")
                } ?: logE("getPLMoviesPage: page=$page of $totalPagesNumber is null")
                page++
            } while (page <= totalPagesNumber)
        }
        return resultList
    }

    fun getPLMoviesPage(movies : TmdbMovies, page: Int?): MovieResultsPage?  = movies.getNowPlayingMovies("pl", page, "PL")
}
