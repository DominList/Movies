package com.dpl.dominlist.movies.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dpl.dominlist.movies.model.Movies

@Composable
fun MoviesList(
    movieItems: Movies,
    onItemClick: (Int) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        items(movieItems) { item ->
            Spacer(modifier = Modifier.fillMaxWidth().height(1.dp))
            Card(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()) {
                Text(
                    text = item.title ?: "no title",
                    fontSize= 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clickable {
                            onItemClick.invoke(item.id)
                        }
                        .padding(15.dp)
                )
            }
            Spacer(modifier = Modifier.fillMaxWidth().height(1.dp))

        }
    }
}