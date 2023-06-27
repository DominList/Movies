package com.dpl.dominlist.movies.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dpl.dominlist.movies.model.MovieItem


@Database(entities = [MovieItem::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
}