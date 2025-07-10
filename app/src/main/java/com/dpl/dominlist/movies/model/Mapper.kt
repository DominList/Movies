package com.dpl.dominlist.movies.model

import android.util.Log
import com.dpl.dominlist.movies.utlis.getTAG
import info.movito.themoviedbapi.model.MovieDb

object Mapper {

    fun map(movieDto: MovieDb): MovieItem? {
        return try {
            mapInternal(movieDto)
        } catch (e : Exception) {
            Log.e(getTAG(), "map id= ${movieDto.id}, title= ${movieDto.title}", e)
            null;
        }
    }

    private fun mapInternal(movieDto: MovieDb): MovieItem = MovieItem(
        id = movieDto.id.toLong(),
        title = movieDto.title,
        description = movieDto.overview,
        posterPath = movieDto.posterPath,
        homePage = movieDto.homepage,
        isAdult = movieDto.isAdult,
        runtime = movieDto.runtime,
        originalTitle = movieDto.originalTitle,
        backdropPath = movieDto.backdropPath,
        voteAverage = movieDto.voteAverage,
        voteCount = movieDto.voteCount,
        releaseDate = movieDto.releaseDate,
        popularity = movieDto.popularity,
    ).apply {
        Log.d(getTAG(), "map: id=$id of $title")
    }
}