package com.dpl.dominlist.movies.utlis

const val REMOTE_MEDIA_BASE_URL : String = "https://image.tmdb.org/t/p/"
const val MEDIUM_SIZE = "w500"
const val LARGE_SIZE = "w780"
const val ORIGINAL_SIZE = "original"

fun getMediumPictureUrl(path: String?) : String? = prepare(path, MEDIUM_SIZE)
fun getLargePictureUrl(path: String?) : String? = prepare(path, LARGE_SIZE)
fun getOriginalPictureUrl(path: String?) : String? = prepare(path, ORIGINAL_SIZE)

private fun prepare(path: String?, size: String) : String? = path?.let{REMOTE_MEDIA_BASE_URL + size + it}