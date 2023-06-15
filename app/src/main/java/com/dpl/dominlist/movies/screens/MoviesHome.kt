package com.dpl.dominlist.movies.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.dpl.dominlist.movies.R
import com.dpl.dominlist.movies.components.MoviesList
import com.dpl.dominlist.movies.viewmodel.MoviesHomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesHome(
    viewModel: MoviesHomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.title))
            },
            actions = {
                Icon(
                    imageVector = Icons.Rounded.Notifications, contentDescription = "Rounded icon"
                )
            },
            colors =  TopAppBarDefaults.centerAlignedTopAppBarColors()
        )

        MoviesList(
            movieItems = viewModel.data.value.wrappedData,
            onItemClick = {
                Log.d("MoviesHome", "Item \"$it\" selected")
                Toast.makeText(
                    context,
                    "Opening \"${
                        viewModel.data.value.wrappedData
                            .first { item -> item.id == it }.title
                    }\"",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
    }
}