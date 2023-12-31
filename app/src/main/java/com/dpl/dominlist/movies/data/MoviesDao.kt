package com.dpl.dominlist.movies.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dpl.dominlist.movies.model.MovieItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * from movies_table")
    fun getMovies(): Flow<List<MovieItem>>

    @Query("SELECT * from movies_table where id =:id")
    suspend fun getMovieById(id: String): MovieItem

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieItem: MovieItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieItem>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(movieItem: MovieItem)

}