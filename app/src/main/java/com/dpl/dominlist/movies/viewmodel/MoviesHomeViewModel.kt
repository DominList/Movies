package com.dpl.dominlist.movies.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dpl.dominlist.movies.data.DataWrapper
import com.dpl.dominlist.movies.model.Movies
import com.dpl.dominlist.movies.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesHomeViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    val data  by mutableStateOf(
        DataWrapper(Movies())
    )

    private var coroutineScope: CoroutineScope? = null

    init {
        getAllMovies()
    }

    private fun getAllMovies() {
        coroutineScope = CoroutineScope(SupervisorJob()+Dispatchers.IO)
        coroutineScope?.launch {
            val movies: Movies = repository.getAllMovies()
            launch(Dispatchers.Main) {
                data.wrappedData = movies
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope?.cancel()
    }
}