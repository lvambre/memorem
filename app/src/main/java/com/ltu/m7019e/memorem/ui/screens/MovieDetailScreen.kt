package com.ltu.m7019e.memorem.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import com.ltu.m7019e.memorem.R
import com.ltu.m7019e.memorem.model.AuthorDetails
import com.ltu.m7019e.memorem.model.MovieReview
import com.ltu.m7019e.memorem.model.MovieVideo
import com.ltu.m7019e.memorem.ui.theme.MemoremTheme
import com.ltu.m7019e.memorem.utils.Constants
import com.ltu.m7019e.memorem.viewmodel.SelectedMovieUiState

const val VIDEO_URI = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"

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
                if (selectedMovieUiState.movieVideos.isEmpty()) {
                    Box {
                        AsyncImage(
                            model = Constants.BACKDROP_IMAGE_BASE_URL +
                                    Constants.BACKDROP_IMAGE_WIDTH + selectedMovieUiState.movie.backdropPath,
                            contentDescription = selectedMovieUiState.movie.title,
                            modifier = modifier,
                            contentScale = ContentScale.Crop
                        )
                    }
                } else {
                    VideoItem()
                }

                Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                    ) {
                        AsyncImage(
                            model = Constants.POSTER_IMAGE_BASE_URL +
                                    Constants.POSTER_IMAGE_WIDTH +
                                    selectedMovieUiState.movie.posterPath,
                            contentDescription = selectedMovieUiState.movie.title,
                            modifier = Modifier
                                .width(92.dp)
                                .height(138.dp),
                        contentScale = ContentScale.Crop)
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = selectedMovieUiState.movie.title,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = dimensionResource(R.dimen.padding_small))
                        )
                        Text(
                            text = selectedMovieUiState.movie.releaseDate.split("-")[0] +
                                    stringResource(R.string.produced_by),
                            style = MaterialTheme.typography.bodySmall,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Text(
                            text = selectedMovieUiState.movie.productionCompanies.joinToString(", ") { it.name },
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Text(
                            text = selectedMovieUiState.movie.genres.joinToString(", ") { it.name },
                            style = MaterialTheme.typography.bodySmall,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = dimensionResource(R.dimen.padding_small),
                                    bottom = dimensionResource(R.dimen.padding_small),
                                    top = 4.dp
                                )
                        )
                    }
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    IconButton(onClick = { goToHomePage(selectedMovieUiState.movie.homepage) }) {
                        Icon(
                            painter = painterResource(R.drawable.internet_icon),
                            contentDescription = stringResource(R.string.homepage))
                    }
                    IconButton(onClick = { openImdbApp(selectedMovieUiState.movie.imdbId) }) {
                        Icon(
                            painter = painterResource(R.drawable.imdb_icon),
                            contentDescription = stringResource(R.string.imdb))
                    }
                }
                Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)))
                Text(
                    text = stringResource(R.string.reviews),
                    style = MaterialTheme.typography.bodyLarge
                )
                MovieReviewsList(
                    movieReviews = selectedMovieUiState.movieReviews,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }

        is SelectedMovieUiState.Loading -> {
            Text(
                text = "Loading...",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
        }

        is SelectedMovieUiState.Error -> {
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
                    .padding(
                        bottom = dimensionResource(R.dimen.padding_small),
                        top = dimensionResource(R.dimen.padding_small),
                        end = dimensionResource(R.dimen.padding_medium)
                    )
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VideosCarousel(
    moviesVideos: List<MovieVideo>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { moviesVideos.size })
    val pointsCount = moviesVideos.size

    Column(
        modifier = modifier
    ) {
        HorizontalPager(
            state = pagerState
        ) {
            moviesVideos.forEach { _ ->
                VideoItem()
            }
        }
        LazyRow(
            modifier = Modifier
                .padding(vertical = dimensionResource(R.dimen.padding_small))
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            items(pointsCount) { index ->
                CurrentPoint(index == pagerState.currentPage)
            }
        }
    }
}

@Composable
fun CurrentPoint(selected: Boolean) {
    val color = if (selected) Color(0xFF3E001C) else Color(0xFFD6C2C4)
    Box(
        modifier = Modifier
            .size(10.dp)
            .padding(horizontal = 4.dp),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(color = color, shape = CircleShape)
        )
    }
}

@Composable
fun VideoItem() {

    // Get the current context
    val context = LocalContext.current

    // Initialize ExoPlayer
    val exoPlayer = ExoPlayer.Builder(context).build()

    // Create a MediaSource
    val mediaSource = remember(VIDEO_URI) {
        MediaItem.fromUri(VIDEO_URI)
    }

    // Set MediaSource to ExoPlayer
    LaunchedEffect(mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
    }

    // Manage lifecycle events
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    // Use AndroidView to embed an Android View (PlayerView) into Compose
    AndroidView(
        factory = { ctx ->
            PlayerView(ctx).apply {
                player = exoPlayer
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp) // Set your desired height
    )
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
                    6.7),
                "really bad movie"
            )
        )
    }
}
