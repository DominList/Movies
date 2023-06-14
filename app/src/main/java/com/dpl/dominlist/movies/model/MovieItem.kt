package com.dpl.dominlist.movies.model

data class MovieItem(
    val id: String,
    val title: String,
    val thumbnailUrl: String,
    val pictureUrl: String,
    var favourite: Boolean = false
)