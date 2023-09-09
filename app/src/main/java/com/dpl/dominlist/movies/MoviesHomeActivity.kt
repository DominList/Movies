package com.dpl.dominlist.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.dpl.dominlist.movies.navigation.MovieNavigation
import com.dpl.dominlist.movies.ui.theme.MoviesTheme
import com.dpl.dominlist.movies.viewmodel.MoviesHomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesHomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            if (savedInstanceState == null) {
                val viewModel: MoviesHomeViewModel = hiltViewModel()

                // todo move to viewModel, launch by comparing dates
                viewModel.fetchData()
            }

            MoviesTheme {
                MovieNavigation()
            }
        }
    }
}
