package com.dpl.dominlist.movies.di

import com.dpl.dominlist.movies.network.MoviesApi
import com.dpl.dominlist.movies.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesMoviesRepository(api: MoviesApi) = MoviesRepository(api)

    @Singleton
    @Provides
    fun providesMoviesApi(): MoviesApi {
        return MoviesApi()
    }
}