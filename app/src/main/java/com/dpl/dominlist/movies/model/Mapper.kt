package com.dpl.dominlist.movies.model

import info.movito.themoviedbapi.model.MovieDb

object Mapper {

    fun map(movieDto: MovieDb): MovieItem = MovieItem(
        id = movieDto.id.toLong(),
        title = movieDto.title,
        description = movieDto.overview,
//          todo          alternativeTitles = it.alternativeTitles,
        posterPath = movieDto.posterPath,
        homePage = movieDto.homepage,
        isAdult = movieDto.isAdult,
//                    casts = it.cast,
        runtime = "",//Date(it.runtime.toLong()),
//                    images = it.getImages()?.map { artwork -> artwork.filePath }
    )
}