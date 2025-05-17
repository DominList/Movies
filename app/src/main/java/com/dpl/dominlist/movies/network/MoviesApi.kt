package com.dpl.dominlist.movies.network

import android.util.Log
import com.dpl.dominlist.movies.BuildConfig
import com.dpl.dominlist.movies.utlis.getTAG
import info.movito.themoviedbapi.TmdbApi
import info.movito.themoviedbapi.TmdbMovies
import info.movito.themoviedbapi.model.MovieDb
import info.movito.themoviedbapi.model.core.MovieResultsPage
import javax.inject.Singleton

@Singleton
class MoviesApi {

    private var moviesApi: TmdbApi? = null

    fun getAllPages(): List<MovieResultsPage> {
        if (moviesApi == null) {
            moviesApi = TmdbApi(BuildConfig.MOVIES_API_KEY)
        }
        val resultList = ArrayList<MovieResultsPage>()
        moviesApi?.movies?.let { moviesDB ->
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
        val nowPlayingMovies = movies.getNowPlayingMovies("pl", page, "PL")
        Log.d(
            "Movie",
            " =================== Loading page = $page  ...  ========================"
        )
        nowPlayingMovies.forEach { movieDb: MovieDb? ->
            movieDb?.let {
        Log.d(getTAG(), "title: ${it.title}")            }
        }
        return nowPlayingMovies
    }
}
