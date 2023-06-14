package com.dpl.dominlist.movies.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.rememberCompositionContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.dpl.dominlist.movies.components.MoviesList
import com.dpl.dominlist.movies.viewmodel.MoviesHomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesHome(
    viewModel: MoviesHomeViewModel = hiltViewModel()
) {
    MoviesList(movieItems = viewModel.data.wrappedData , onItemClick = {
        Log.d("MoviesHome", "Item \"$it\" selected")
    })
}