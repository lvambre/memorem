package com.ltu.m7019e.memorem.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ltu.m7019e.memorem.R
import com.ltu.m7019e.memorem.database.Movies
import com.ltu.m7019e.memorem.model.Movie
import com.ltu.m7019e.memorem.ui.theme.MemoremTheme
import com.ltu.m7019e.memorem.utils.Constants

@Composable
fun StartScreen(
    onMovieItemClicked: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        // For now, both lists are the same because we didn't retrieve all the different movies
        val popularMovies = Movies().getMovies()
        val topRatedMovies = Movies().getMovies()

        Text(
            text = stringResource(id = R.string.popular_movies),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(start = 8.dp)
        )
        MovieList(
            movies = popularMovies,
            onMovieItemClicked = onMovieItemClicked
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(id = R.string.top_rated_movies),
            style = MaterialTheme.typography.headlineSmall
        )
        MovieList(
            movies = topRatedMovies,
            onMovieItemClicked = onMovieItemClicked
        )
    }
}

@Composable
fun MovieList(movies: List<Movie>,
              onMovieItemClicked: (Movie) -> Unit,
              modifier: Modifier = Modifier
) {
    LazyRow (
        modifier = modifier
        ) {
        items(movies) {
            MovieItem(
                movie = it,
                onMovieItemClicked = onMovieItemClicked,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)))
        }
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    onMovieItemClicked: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val defaultHeight = 155.dp
    val expandedHeight = 300.dp
    val height = if (!expanded) defaultHeight else expandedHeight
    
    Card(modifier = modifier
        .width(120.dp)
        .height(height)
        .clickable { expanded = !expanded },
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = Constants.POSTER_IMAGE_BASE_URL +
                            Constants.POSTER_IMAGE_WIDTH + movie.posterPath,
                    contentDescription = movie.title,
                    modifier = Modifier
                        .width(dimensionResource(R.dimen.image_width))
                        .height(dimensionResource(R.dimen.image_height)),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)))
            if(expanded) {
               MovieInfo(
                   onMovieItemClicked = onMovieItemClicked,
                   movie = movie)
            }
            Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)))
        }
    }
}

@Composable
fun MovieInfo(
    movie: Movie,
    onMovieItemClicked: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        // CLICKABLE TEXT TO REDIRECT YOU TO THE MOVIE DETAIL SCREEN
        Text(
            text = movie.title,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_small))
                .fillMaxWidth()
                .clickable { onMovieItemClicked(movie) },
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = movie.releaseDate,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(R.dimen.padding_small))
        )
    }
}

@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun MovieItemPreview() {
    MemoremTheme {
         StartScreen(onMovieItemClicked = {})
    }
}