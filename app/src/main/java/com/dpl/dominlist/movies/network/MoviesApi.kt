package com.dpl.dominlist.movies.network

import android.util.Log
import com.dpl.dominlist.movies.utlis.Constants.MOVIES_API_KEY
import info.movito.themoviedbapi.TmdbApi
import info.movito.themoviedbapi.TmdbMovies
import info.movito.themoviedbapi.model.MovieDb
import info.movito.themoviedbapi.model.core.MovieResultsPage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Singleton
class MoviesApi {

    @Volatile
    private var moviesApi: TmdbApi? = null

    suspend fun getAllPages(): List<MovieResultsPage> {
        if (moviesApi == null) {
            moviesApi = TmdbApi(MOVIES_API_KEY)
        }

        val resultList = ArrayList<MovieResultsPage>()
        moviesApi?.movies?.let { moviesDB ->
            var page = 1
            val totalPagesNumber = loadResultsPerPage(moviesDB, page)?.totalPages ?: 0
            while (page < totalPagesNumber) {
                Thread.sleep(500)
                page++
                loadResultsPerPage(moviesDB, page)?.let {
                    resultList.add(it)
                }
            }
        }
        Log.d("Movie", " ===================  END LIST  ========================")
        return resultList

    }


    private suspend fun loadResultsPerPage(movies: TmdbMovies, page: Int): MovieResultsPage? {
        val nowPlayingMovies = movies.getNowPlayingMovies("pl", page, "PL")
        Log.d(
            "Movie",
            " =================== Loading page = $page  ...  ========================"
        )
        nowPlayingMovies.forEach { movieDb: MovieDb? ->
            movieDb?.let { Log.d("Movie", it.title) }
        }
        return nowPlayingMovies
    }
}
