package com.ltu.m7019e.memorem.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ltu.m7019e.memorem.R
import com.ltu.m7019e.memorem.model.Movie
import com.ltu.m7019e.memorem.ui.theme.MemoremTheme
import com.ltu.m7019e.memorem.utils.Constants
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
    var listDisplayed by remember { mutableStateOf(true) }

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
                    if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = null
                )
            }
            IconButton(onClick = { listDisplayed = !listDisplayed }) {
                Icon(
                    Icons.Filled.List,
                    contentDescription = null
                )
            }
        }

        if (expanded) {
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
        if(listDisplayed) {
            MovieList(
                movieListUiState = movieListUiState,
                onMovieItemClicked = onMovieItemClicked,
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_small)))
        }
        else {
            MovieGrid(
                movieListUiState = movieListUiState,
                onMovieItemClicked = onMovieItemClicked,
                modifier = Modifier)
        }
    }
}

@Composable
fun MovieList(movieListUiState: MovieListUiState,
              onMovieItemClicked: (Movie) -> Unit,
              modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ){
        when(movieListUiState) {
            is MovieListUiState.Success -> {
                items(movieListUiState.movies) {
                    MovieItemList(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieItemList(
    movie: Movie,
    onMovieItemClicked: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(140.dp),
        onClick = { onMovieItemClicked(movie) }
    ) {
        Row {
            Box {
                AsyncImage(
                    model = Constants.POSTER_IMAGE_BASE_URL +
                            Constants.POSTER_IMAGE_WIDTH + movie.posterPath,
                    contentDescription = movie.title,
                    modifier = modifier
                        .width(92.dp)
                        .height(138.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
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
                    MovieItemGrid(
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

@Composable
fun MovieItemGrid(
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

@Preview
@Composable
fun MovieItemListPreview() {
    MemoremTheme {
        MovieItemList(
            movie = Movie(
                id = 1096197,
                title = "No Way Up",
                posterPath = "/hu40Uxp9WtpL34jv3zyWLb5zEVY.jpg",
                overview = "Characters from different backgrounds are thrown together when the plane they're travelling on crashes into the Pacific Ocean. A nightmare fight for survival ensues with the air supply running out and dangers creeping in from all sides."),
            onMovieItemClicked = {})
    }
}