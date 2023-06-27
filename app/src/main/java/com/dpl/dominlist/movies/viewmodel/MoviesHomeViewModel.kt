package com.dpl.dominlist.movies.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dpl.dominlist.movies.model.MovieItem
import com.dpl.dominlist.movies.repository.MoviesRepository
import com.dpl.dominlist.movies.service.MoviesService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesHomeViewModel @Inject constructor(
    private val repository: MoviesRepository,
    private val updateService: MoviesService
) : ViewModel() {

//    val data: MutableState<DataWrapper<Movies>> = mutableStateOf(
//        DataWrapper(Movies())
//    )


    private val _moviesList = MutableStateFlow(emptyList<MovieItem>())
    val movieList = _moviesList.asStateFlow()


    init {
        getAllMovies()
    }

    fun fetchData() {
        viewModelScope.launch {
            updateService.fetchData()
        }
    }

    private fun getAllMovies() {
        Log.d(this.javaClass.simpleName, "getAllMovies()")
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllMovies().distinctUntilChanged().collect {
                _moviesList.value = it
            }
        }
    }
}