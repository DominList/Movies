package com.dpl.dominlist.movies.repository

import com.dpl.dominlist.movies.model.MovieItem
import com.dpl.dominlist.movies.model.Movies
import com.dpl.dominlist.movies.network.MoviesApi
import info.movito.themoviedbapi.model.MovieDb
import info.movito.themoviedbapi.model.core.MovieResultsPage
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val api: MoviesApi) {

    private val moviesData = Movies()

    suspend fun getAllMovies(): Movies {
        val moviePages = api.getAllPages()
        if (moviePages.isNotEmpty()) {
            api.getAllPages().forEach{ page ->
                extractPage(page).apply { moviesData.addAll(this) }
            }
        }
        return moviesData
    }

    private suspend fun extractPage(page: MovieResultsPage): Movies {
        val movies = Movies()
        page.forEach { movieDb: MovieDb? ->
            movieDb?.let {
                MovieItem(
                    id = it.imdbID,
                    title = it.title,
                    thumbnailUrl = it.posterPath,
                    pictureUrl = it.backdropPath
                ).apply { movies.add(this) }
            }
        }
        return movies
    }

}
