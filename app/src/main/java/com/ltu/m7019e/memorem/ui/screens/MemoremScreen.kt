package com.ltu.m7019e.memorem.ui.screens

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ltu.m7019e.memorem.R
import com.ltu.m7019e.memorem.ui.theme.MemoremTheme
import com.ltu.m7019e.memorem.viewmodel.MemoremViewModel

enum class MemoremScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Detail(title = R.string.movie_details),
    Search(title = R.string.search),
    Favorite(title = R.string.favorite_movies),
    Rating(title = R.string.rating_movies)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MemoremApp(
    viewModel: MemoremViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MemoremScreen.valueOf(
        backStackEntry?.destination?.route ?: MemoremScreen.Start.name
    )

    Scaffold(
        topBar =  {
            MemoremTopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null
                        && currentScreen != MemoremScreen.Start,
                navigateUp = { navController.navigateUp() }
                )
        },
        bottomBar = { MemoremBottomAppBar(navController) }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = MemoremScreen.Start.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = MemoremScreen.Start.name) {
                StartScreen(
                    onMovieItemClicked = { movie ->
                        viewModel.setSelectedMovie(movie)
                        navController.navigate(MemoremScreen.Detail.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }

            composable(route = MemoremScreen.Detail.name) {
                uiState.selectedMovie?.let { movie ->
                    MovieDetailScreen(
                        movie = movie,
                        modifier = Modifier)
                }
            }

            composable(route = MemoremScreen.Search.name) {
                SearchMovieScreen()
            }

            composable(route = MemoremScreen.Favorite.name) {
                FavoriteMoviesScreen()
            }

            composable(route = MemoremScreen.Rating.name) {
                RatingMovieScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoremTopAppBar(
    currentScreen: MemoremScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = currentScreen.title),
                style = MaterialTheme.typography.headlineMedium
            )
        },
        modifier = modifier,
        navigationIcon = {
            if(canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun MemoremBottomAppBar(
    navController: NavHostController,
    modifier: Modifier = Modifier) {
    BottomAppBar {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(id = R.dimen.padding_medium),
                    end = dimensionResource(R.dimen.padding_medium)
                )
        ) {
            IconButton(onClick = { navController.navigate(MemoremScreen.Start.name) }) {
                Icon(
                    Icons.Rounded.Home,
                    contentDescription = "Home page")
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { navController.navigate(MemoremScreen.Search.name) }) {
                Icon(
                    Icons.Rounded.Search,
                    contentDescription = "Search page")
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { navController.navigate(MemoremScreen.Favorite.name) }) {
                Icon(
                    Icons.Rounded.Favorite,
                    contentDescription = "Favorite movies page")
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { navController.navigate(MemoremScreen.Rating.name) }) {
                Icon(
                    Icons.Rounded.Star,
                    contentDescription = "Rating movies page")
            }
        }
    }
}

@Preview
@Composable
fun StartScreenPreview() {
    MemoremTheme {
        StartScreen(onMovieItemClicked = {})
    }
}