package com.dpl.dominlist.movies.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import info.movito.themoviedbapi.model.AlternativeTitle

@Entity(tableName = "movies_table") // optional name
data class MovieItem(
    @PrimaryKey val id: String,
    @ColumnInfo val title: String?, // column annotation can contain an optional name for db
//    @ColumnInfo val alternativeTitles: List<AlternativeTitle>?, // todo use type converter
    @ColumnInfo val description: String?,
    @ColumnInfo val posterPath: String?,
    @ColumnInfo val homePage: String?,
    @ColumnInfo var favourite: Boolean = false
)