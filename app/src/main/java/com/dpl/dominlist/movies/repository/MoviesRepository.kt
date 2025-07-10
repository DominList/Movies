package com.dpl.dominlist.movies.repository

import com.dpl.dominlist.movies.data.MoviesDao
import com.dpl.dominlist.movies.model.MovieItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val moviesDao: MoviesDao) {

    suspend fun addMovie(movieItem: MovieItem) = moviesDao.insert(movieItem)

    suspend fun updateMovie(movieItem: MovieItem) = moviesDao.update(movieItem)

    suspend fun addOrUpdateMovies(movies: List<MovieItem>) : List<Long> = moviesDao.insertAll(movies)

    fun getAllMovies() = moviesDao.getMovies().flowOn(Dispatchers.IO).conflate()
}
