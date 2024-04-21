package com.ltu.m7019e.memorem.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ltu.m7019e.memorem.R
import com.ltu.m7019e.memorem.model.Movie
import com.ltu.m7019e.memorem.utils.Constants
import com.ltu.m7019e.memorem.viewmodel.MovieListUiState

@Composable
fun MovieList(movieListUiState: MovieListUiState,
              onMovieItemClicked: (Movie) -> Unit,
              modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
    ) {
        when(movieListUiState) {
            is MovieListUiState.Success -> {
                items(movieListUiState.movies) {
                    MovieItem(
                        movie = it,
                        onMovieItemClicked = onMovieItemClicked,
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)))
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

@Composable
fun MovieItem(
    movie: Movie,
    onMovieItemClicked: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    Box {
        AsyncImage(
            model = Constants.POSTER_IMAGE_BASE_URL +
                    Constants.POSTER_IMAGE_WIDTH + movie.posterPath,
            contentDescription = movie.title,
            modifier = modifier
                .width(92.dp)
                .height(138.dp)
                .clickable { onMovieItemClicked(movie) },
            contentScale = ContentScale.Crop
        )
    }


}