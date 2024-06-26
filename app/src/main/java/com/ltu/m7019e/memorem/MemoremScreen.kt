package com.ltu.m7019e.memorem

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ltu.m7019e.memorem.ui.screens.FavoriteMoviesScreen
import com.ltu.m7019e.memorem.ui.screens.HomeScreen
import com.ltu.m7019e.memorem.ui.screens.MovieDetailsScreen
import com.ltu.m7019e.memorem.ui.theme.MemoremTheme
import com.ltu.m7019e.memorem.utils.Constants
import com.ltu.m7019e.memorem.viewmodel.MemoremViewModel
import com.ltu.m7019e.memorem.viewmodel.MovieCategory

enum class MemoremScreen(@StringRes val title: Int) {
    List(title = R.string.app_name),
    Details(title = R.string.movie_details),
    Favorite(title = R.string.favorite_movies)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MemoremApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MemoremScreen.valueOf(
        backStackEntry?.destination?.route ?: MemoremScreen.List.name
    )
    val memoremViewModel: MemoremViewModel = viewModel(factory = MemoremViewModel.Factory)

    Scaffold(
        topBar = {
            MemoremTopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = currentScreen == MemoremScreen.Details,
                navigateUp = { navController.navigateUp() }
                )
        },
        bottomBar = { MemoremBottomAppBar(memoremViewModel, navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MemoremScreen.List.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = MemoremScreen.List.name) {
                HomeScreen(
                    movieListUiState = memoremViewModel.movieListUiState,
                    onMovieItemClicked = { movie ->
                        memoremViewModel.setSelectedMovieDetails(movie)
                        navController.navigate(MemoremScreen.Details.name)
                    },
                    onMovieListClicked = { category ->
                        memoremViewModel.getListMovies(category)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_small))
                )
            }

            composable(route = MemoremScreen.Details.name) {
                val context = LocalContext.current
                MovieDetailsScreen(
                    memoremViewModel = memoremViewModel,
                    goToHomePage = { homepageUrl: String -> goToHomepage(context, homepageUrl) },
                    openImdbApp = { imdb: String -> openImdbApp(context, imdb) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }

            composable(route = MemoremScreen.Favorite.name) {
                FavoriteMoviesScreen(
                    movieListUiState = memoremViewModel.movieListUiState,
                    onMovieItemClicked = { movie ->
                        memoremViewModel.setSelectedMovieDetails(movie)
                        navController.navigate(MemoremScreen.Details.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_small))
                )
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
                style = MaterialTheme.typography.headlineLarge
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
    memoremViewModel: MemoremViewModel,
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
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {
                navController.navigate(MemoremScreen.List.name)
                memoremViewModel.getListMovies(MovieCategory.ALL_MOVIES)
            }) {
                Icon(
                    Icons.Rounded.Home,
                    contentDescription = "Home page")
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {
                navController.navigate(MemoremScreen.Favorite.name)
                memoremViewModel.getListMovies(MovieCategory.FAVORITE_MOVIES)
            }) {
                Icon(
                    Icons.Rounded.Favorite,
                    contentDescription = "Favorite movies page")
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

private fun goToHomepage(
    context: Context,
    homepageUrl: String
) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(homepageUrl)
    }

    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.open_homepage_with)
        )
    )
}

private fun openImdbApp(context: Context, imdb: String) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(Constants.IMDB_BASE_URL + imdb)
    }

    intent.setPackage("com.imdb.mobile")

    try {
        context.startActivity(intent)
    } catch (e: android.content.ActivityNotFoundException) {
        // If the app is not installed on the phone, redirect to the browser
        goToHomepage(context, Constants.IMDB_BASE_URL + imdb)
    }
}

@Preview
@Composable
fun StartScreenPreview() {
    MemoremTheme {
    }
}