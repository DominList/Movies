package com.dpl.dominlist.movies.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dpl.dominlist.movies.model.Movies

@Composable
fun MoviesList(
    movieItems: Movies,
    onItemClick: (Int) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Magenta)
    ) {
        items(movieItems) { item ->
            Text(
                text = item.title?:"",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick.invoke(item.id)
                    }
                    .padding(16.dp)
            )
        }
    }
}