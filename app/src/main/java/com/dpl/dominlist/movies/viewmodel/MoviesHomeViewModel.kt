package com.dpl.dominlist.movies.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dpl.dominlist.movies.data.DataWrapper
import com.dpl.dominlist.movies.model.Movies
import com.dpl.dominlist.movies.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesHomeViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    private val coroutineScope: CoroutineScope?

    val data by mutableStateOf(
        DataWrapper(Movies())
    )

    init {
        coroutineScope = CoroutineScope(Dispatchers.Unconfined)
        getAllMovies()
    }

    private fun getAllMovies() {
        viewModelScope.launch {
            val movies: Movies = repository.getAllMovies(coroutineScope)
            data.wrappedData = movies
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope?.cancel()
    }
}