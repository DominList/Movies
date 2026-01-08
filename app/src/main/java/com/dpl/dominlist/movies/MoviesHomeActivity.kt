package com.dpl.dominlist.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dpl.dominlist.movies.navigation.MovieNavigation
import com.dpl.dominlist.movies.ui.theme.MoviesTheme
import com.dpl.dominlist.movies.utlis.logDebug
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesHomeActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            logDebug("setContent: ")
            MoviesTheme {
                MovieNavigation()
            }
        }
    }
}
