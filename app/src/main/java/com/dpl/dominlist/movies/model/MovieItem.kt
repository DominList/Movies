package com.dpl.dominlist.movies.model

data class MovieItem(
    val id: Int,
    val title: String?,
    val posterPath: String?,
    val homePage: String?,
    var favourite: Boolean = false
)