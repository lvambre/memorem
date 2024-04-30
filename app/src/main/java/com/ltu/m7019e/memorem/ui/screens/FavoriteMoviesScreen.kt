package com.ltu.m7019e.memorem.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ltu.m7019e.memorem.model.Movie
import com.ltu.m7019e.memorem.viewmodel.MovieListUiState

@Composable
fun FavoriteMoviesScreen(
    movieListUiState: MovieListUiState,
    onMovieItemClicked: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    MovieGrid(
        movieListUiState = movieListUiState,
        onMovieItemClicked = onMovieItemClicked,
        modifier = modifier)
}