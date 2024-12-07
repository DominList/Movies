package com.dpl.dominlist.movies.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import info.movito.themoviedbapi.model.AlternativeTitle
import java.util.Date

@Entity(tableName = "movies_table") // optional name
data class MovieItem(
    @PrimaryKey val id: Long,
    @ColumnInfo val title: String? = null, // column annotation can contain an optional name for db
//    @ColumnInfo val alternativeTitles: List<AlternativeTitle>?, // todo use type converter
    @ColumnInfo val description: String? = null,
    @ColumnInfo val posterPath: String? = null,
    @ColumnInfo val homePage: String? = null,
    @ColumnInfo val isAdult: Boolean? = null,
    @ColumnInfo val runtime: String? = null,
//    @ColumnInfo val images: List<String>?,
    @ColumnInfo var favourite: Boolean = false
)