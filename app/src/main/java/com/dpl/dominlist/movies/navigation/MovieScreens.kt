package com.dpl.dominlist.movies.navigation

enum class MovieScreens {
    Movies,
    Poster,
    Detail;

    companion object {
        fun fromRoute(route: String?) : MovieScreens
        = when (route?.substringBefore(delimiter = "/")) {
            Movies.name -> Movies
            Detail.name -> Detail
            null -> Movies
            else -> throw IllegalArgumentException("Route $route is not valid argument!")
        }
    }
}