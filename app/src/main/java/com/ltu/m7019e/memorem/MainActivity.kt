package com.ltu.m7019e.memorem

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ltu.m7019e.memorem.database.Movies
import com.ltu.m7019e.memorem.screens.MovieList
import com.ltu.m7019e.memorem.ui.theme.MemoremTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemoremTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MemoremApp()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MemoremApp() {
    Scaffold(
        topBar =  { MemoremTopAppBar() },
        bottomBar = { MemoremBottomAppBar() }
    ) {
        Column {
            val popularMovies = Movies().getMovies()
            val topRatedMovies = Movies().getMovies()
            Spacer(modifier = Modifier.height(80.dp))
            Text(
                text = stringResource(id = R.string.popular_movies),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
            MovieList(movies = popularMovies)

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = stringResource(id = R.string.top_rated_movies),
                style = MaterialTheme.typography.headlineSmall
            )
            MovieList(movies = topRatedMovies)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoremTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineMedium
            )
        }
    )
}

@Composable
fun MemoremBottomAppBar(modifier: Modifier = Modifier) {
    BottomAppBar {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.padding_medium),
                    end = dimensionResource(R.dimen.padding_medium)
                )
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Rounded.Home,
                    contentDescription = "Home page")
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Rounded.Search,
                    contentDescription = "Search page")
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Rounded.Star,
                    contentDescription = "Favorite movies page")
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Rounded.AccountCircle,
                    contentDescription = "Profile page")
            }
        }
    }
}

@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun MemoremPreview() {
    MemoremTheme(darkTheme = false) {
        MemoremApp()
    }
}

@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun MemoremPreviewDark() {
    MemoremTheme(darkTheme = true) {
        MemoremApp()
    }
}