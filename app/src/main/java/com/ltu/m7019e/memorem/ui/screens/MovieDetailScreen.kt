package com.ltu.m7019e.memorem.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.ltu.m7019e.memorem.R
import com.ltu.m7019e.memorem.model.Movie
import com.ltu.m7019e.memorem.utils.Constants

@Composable
fun MovieDetailScreen(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Box {
            AsyncImage(
                model = Constants.BACKDROP_IMAGE_BASE_URL +
                        Constants.BACKDROP_IMAGE_WIDTH + movie.backdropPath,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = movie.title,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_small))
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)))
        Text(
            text = stringResource(R.string.release_date) + movie.releaseDate,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(R.dimen.padding_small))
        )
        Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)))
        Text(
            text = movie.overview,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(R.dimen.padding_small))
        )
    }
}