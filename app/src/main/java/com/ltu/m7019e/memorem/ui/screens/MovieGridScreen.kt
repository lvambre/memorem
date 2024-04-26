package com.ltu.m7019e.memorem.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ltu.m7019e.memorem.R
import com.ltu.m7019e.memorem.model.Movie
import com.ltu.m7019e.memorem.viewmodel.MovieCategory
import com.ltu.m7019e.memorem.viewmodel.MovieListUiState

@Composable
fun HomeScreen(
    movieListUiState: MovieListUiState,
    onMovieItemClicked: (Movie) -> Unit,
    onMovieListClicked: (MovieCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf(MovieCategory.ALL_MOVIES) }

    Column(
        modifier = modifier
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(selectedCategory.title),
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    if(expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = null
                )
            }
        }
        if(expanded) {
            Column(
                modifier = Modifier
                    .padding(bottom = dimensionResource(R.dimen.padding_small))
            ) {
                TextButton(onClick = {
                    onMovieListClicked(MovieCategory.ALL_MOVIES)
                    selectedCategory = MovieCategory.ALL_MOVIES
                    expanded = !expanded
                }) {
                    Text(
                        text = stringResource(R.string.all_movies),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Divider(Modifier.padding(vertical = 4.dp))
                TextButton(onClick = {
                    onMovieListClicked(MovieCategory.POPULAR_MOVIES)
                    selectedCategory = MovieCategory.POPULAR_MOVIES
                    expanded = !expanded
                }) {
                    Text(
                        text = stringResource(R.string.popular_movies),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Divider(Modifier.padding(vertical = 4.dp))
                TextButton(onClick = {
                    onMovieListClicked(MovieCategory.TOP_RATED_MOVIES)
                    selectedCategory = MovieCategory.TOP_RATED_MOVIES
                    expanded = !expanded
                }) {
                    Text(
                        text = stringResource(R.string.top_rated_movies),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
        MovieGrid(
            movieListUiState = movieListUiState,
            onMovieItemClicked = onMovieItemClicked,
            modifier = Modifier)
    }

}

@Composable
fun MovieGrid(
    movieListUiState: MovieListUiState,
    onMovieItemClicked: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = modifier
    ) {
        when(movieListUiState) {
            is MovieListUiState.Success -> {
                items(movieListUiState.movies) {
                    MovieItem(
                        movie = it,
                        onMovieItemClicked = onMovieItemClicked,
                        modifier = Modifier.padding(4.dp))
                }
            }

            is MovieListUiState.Loading -> {
                item {
                    Text(
                        text = "Loading...",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .fillMaxHeight()
                    )
                }
            }

            is MovieListUiState.Error -> {
                item {
                    Text(
                        text = "Error: Something went wrong!",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .fillMaxHeight()
                    )
                }
            }
        }
    }
}