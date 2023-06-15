package com.dpl.dominlist.movies.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dpl.dominlist.movies.data.DataWrapper
import com.dpl.dominlist.movies.model.Movies
import com.dpl.dominlist.movies.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesHomeViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    val data: MutableState<DataWrapper<Movies>> = mutableStateOf(
        DataWrapper(Movies())
    )

    init {
        getAllMovies()
    }

    private fun getAllMovies() {
        viewModelScope.launch {
            data.value = repository.getAllMovies()
        }
    }
}