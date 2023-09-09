package com.dpl.dominlist.movies.viewmodel

import android.content.SharedPreferences
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
    val movieList get() =_moviesList.asStateFlow()


    init {
        Log.d(this.javaClass.simpleName, "init viewModel: getALlMovies()")
        getAllMovies()
    }

    fun fetchData() {
        viewModelScope.launch {
            // todo use sharedPrefs for limiting data fetch with date
            updateService.fetchData()
        }
    }

    private fun getAllMovies() {
        Log.d(this.javaClass.simpleName, "getAllMovies()")
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllMovies().distinctUntilChanged().collect {
                _moviesList.value = it
                Log.d(this.javaClass.simpleName, "getAllMovies() inside viewModel scope")
            }
        }
    }
}