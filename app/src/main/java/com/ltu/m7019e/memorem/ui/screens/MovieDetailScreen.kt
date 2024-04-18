package com.ltu.m7019e.memorem.ui.screens

import androidx.compose.foundation.clickable
import com.ltu.m7019e.memorem.viewmodel.SelectedMovieUiState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ltu.m7019e.memorem.R
import com.ltu.m7019e.memorem.utils.Constants

@Composable
fun MovieDetailScreen(
    selectedMovieUiState: SelectedMovieUiState,
    goToHomePage: (String) -> Unit,
    openImdbApp: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    when (selectedMovieUiState) {
        is SelectedMovieUiState.Success -> {
            Column(
                modifier = modifier
                    .verticalScroll(rememberScrollState())
            ) {
                Box {
                    AsyncImage(
                        model = Constants.BACKDROP_IMAGE_BASE_URL +
                                Constants.BACKDROP_IMAGE_WIDTH + selectedMovieUiState.movie.backdropPath,
                        contentDescription = selectedMovieUiState.movie.title,
                        modifier = modifier,
                        contentScale = ContentScale.Crop
                    )
                }
                Text(
                    text = selectedMovieUiState.movie.title,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.padding_small))
                )
                Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)))
                Text(
                    text = stringResource(R.string.release_date) + selectedMovieUiState.movie.releaseDate,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                if (selectedMovieUiState.movie.genres.size == 1) {
                    Text(
                        text = stringResource(R.string.genre) + selectedMovieUiState.movie.genres[0].name,
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = dimensionResource(R.dimen.padding_small))
                    )
                } else {
                    Text(
                        text = stringResource(R.string.genres) +
                                selectedMovieUiState.movie.genres.joinToString(", ") { it.name },
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = dimensionResource(R.dimen.padding_small))
                    )
                }
                Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)))
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                ) {
                    Text(
                        text = selectedMovieUiState.movie.overview,
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.padding_small))
                    )
                }
                Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)))
                if (selectedMovieUiState.movie.homepage != "") {
                    Text(
                        text = stringResource(R.string.homepage),
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.Underline,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .clickable { goToHomePage(selectedMovieUiState.movie.homepage) }
                            .fillMaxWidth()
                            .padding(top = dimensionResource(R.dimen.padding_small))
                    )
                }
                Text(
                    text = stringResource(R.string.imdb),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clickable { openImdbApp(selectedMovieUiState.movie.imdbId) }
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }

        is SelectedMovieUiState.Loading -> {
            Text(
                text = "Loading...",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(16.dp)
            )
        }

        is SelectedMovieUiState.Error -> {
            Text(
                text = "Error: Something went wrong!",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}