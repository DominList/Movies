package com.dpl.dominlist.movies.model

import com.dpl.dominlist.movies.utlis.logD
import com.dpl.dominlist.movies.utlis.logE
import info.movito.themoviedbapi.model.MovieDb

object Mapper {

    fun map(movieDto: MovieDb): MovieItem? {
        return try {
            mapInternal(movieDto)
        } catch (e : Exception) {
            logE("map id= ${movieDto.id}, title= ${movieDto.title}", e)
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
        logD("map: id=$id of $title")
    }
}