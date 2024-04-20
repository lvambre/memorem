package com.ltu.m7019e.memorem.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ltu.m7019e.memorem.R
import com.ltu.m7019e.memorem.model.AuthorDetails
import com.ltu.m7019e.memorem.model.MovieReview
import com.ltu.m7019e.memorem.ui.theme.MemoremTheme
import com.ltu.m7019e.memorem.utils.Constants
import com.ltu.m7019e.memorem.viewmodel.SelectedMovieUiState

@Composable
fun MovieDetailsScreen(
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
                Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)))
                Text(
                    text = stringResource(R.string.reviews),
                    style = MaterialTheme.typography.headlineMedium
                )
                MovieReviewsList(
                    movieReviews = selectedMovieUiState.movieReviews,
                    modifier = Modifier
                        .fillMaxWidth())
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

@Composable
fun MovieReviewsList(
    movieReviews: List<MovieReview>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(movieReviews) {
            MovieReviewItem(
                movieReview = it,
                modifier = Modifier
                    .padding(top = dimensionResource(R.dimen.padding_small), end = dimensionResource (R.dimen.padding_medium))
                    .fillMaxWidth())
        }
    }
}

@Composable
fun MovieReviewItem(
    movieReview: MovieReview,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .width(300.dp)
            .heightIn(
                min = 140.dp,
                max = if (expanded) Int.MAX_VALUE.dp else 140.dp
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row {
                /* AsyncImage(
                    model = movieReview.authorDetails.avatarPath,
                    contentDescription = stringResource(R.string.avatar_path)
                ) */
                Text(
                    text = movieReview.author + " (" + movieReview.authorDetails.username + ")",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                )
            }
            Row {
                Text(
                    text = movieReview.authorDetails.rating.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(end = 5.dp)
                )
                Icon(imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    modifier = Modifier
                        .size(18.dp))
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
            Text(
                text = if (expanded) movieReview.content else movieReview.content.take(150) + "...",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(bottom = dimensionResource(R.dimen.padding_small))
                    .clickable { expanded = !expanded },
                maxLines = if (expanded) Int.MAX_VALUE else 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun MovieReviewItemPreview() {
    MemoremTheme {
        MovieReviewItem(
            MovieReview(
                "Wuchak",
                AuthorDetails(
                    "Wuchak",
                    "/4KVM1VkqmXLOuwj1jjaSdxbvBDk.jpg",
                    6.7),
                "really bad movie",
                "2019-02-20T11:34:23.418Z",
                "https://www.themoviedb.org/review/5c6d3b3f0e0a2617779faa78"
            )
        )
    }
}
