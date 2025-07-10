package com.dpl.dominlist.movies.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
data class MovieItem(
    @PrimaryKey val id: Long,
    @ColumnInfo val title: String? = null,
    @ColumnInfo val description: String? = null,
    @ColumnInfo val posterPath: String? = null,
    @ColumnInfo val homePage: String? = null,
    @ColumnInfo val isAdult: Boolean? = null,
    @ColumnInfo val runtime: Int? = null,
    @ColumnInfo var favourite: Boolean = false,
    @ColumnInfo val originalTitle: String? = null,
    @ColumnInfo val backdropPath: String? = null,
    @ColumnInfo val voteAverage: Float? = null,
    @ColumnInfo val voteCount: Int? = null,
    @ColumnInfo val releaseDate: String? = null,
    @ColumnInfo val popularity: Float? = null
)