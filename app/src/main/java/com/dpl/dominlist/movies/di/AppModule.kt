package com.dpl.dominlist.movies.di

import android.content.Context
import androidx.room.Room
import com.dpl.dominlist.movies.BuildConfig
import com.dpl.dominlist.movies.data.MoviesDao
import com.dpl.dominlist.movies.data.MoviesDatabase
import com.dpl.dominlist.movies.network.MoviesApi
import com.dpl.dominlist.movies.repository.MoviesRepository
import com.dpl.dominlist.movies.service.MoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import info.movito.themoviedbapi.TmdbApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesMoviesDao(moviesDatabase: MoviesDatabase): MoviesDao =
        moviesDatabase.moviesDao()

    @Singleton
    @Provides
    fun providesMoviesAppDatabase(@ApplicationContext context: Context): MoviesDatabase =
        Room.databaseBuilder(context, MoviesDatabase::class.java, "movies_database")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun providesMoviesService(api: MoviesApi, dao: MoviesDao): MoviesService =
        MoviesService(api, dao)

    @Singleton
    @Provides
    fun providesMoviesRepository(dao: MoviesDao): MoviesRepository = MoviesRepository(dao)

//    @Singleton
//    @Provides
//    fun providesTmDbApi(): TmdbApi {
//        return TmdbApi(BuildConfig.MOVIES_API_KEY)
//    }

    @Singleton
    @Provides
    fun providesMoviesApi(): MoviesApi {
        return MoviesApi()
    }
}