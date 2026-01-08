package com.dpl.dominlist.movies.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dpl.dominlist.movies.model.MovieItem
import com.dpl.dominlist.movies.repository.MoviesRepository
import com.dpl.dominlist.movies.service.MoviesService
import com.dpl.dominlist.movies.utlis.getTAG
import com.dpl.dominlist.movies.utlis.logDebug
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


    private val _moviesList = MutableStateFlow(emptyList<MovieItem>())
    val movieList get() =_moviesList.asStateFlow()


    init {
        Log.d(this.javaClass.simpleName, "init viewModel: getALlMovies()")
        getAllMovies()
        // fixme use sharedPrefs for limiting data fetch with date
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            // todo use sharedPrefs for limiting data fetch with date
            updateService.fetchData()
        }
    }

    private fun getAllMovies() {
        logDebug(msg = "getAllMovies()")
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllMovies().distinctUntilChanged().collect {
                _moviesList.value = it
                for (item in it) Log.d(this@MoviesHomeViewModel.getTAG(), item.toString())
            }
        }
    }

}