package com.ltu.m7019e.memorem.viewmodel

import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ltu.m7019e.memorem.MemoremApplication
import com.ltu.m7019e.memorem.R
import com.ltu.m7019e.memorem.database.MoviesRepository
import com.ltu.m7019e.memorem.database.SavedMoviesRepository
import com.ltu.m7019e.memorem.model.Movie
import com.ltu.m7019e.memorem.model.MovieDetails
import com.ltu.m7019e.memorem.model.MovieReview
import com.ltu.m7019e.memorem.model.MovieVideo
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

enum class MovieCategory(@StringRes val title: Int) {
    ALL_MOVIES(title = R.string.all_movies),
    POPULAR_MOVIES(title = R.string.popular_movies),
    TOP_RATED_MOVIES(title = R.string.top_rated_movies),
    FAVORITE_MOVIES(title = R.string.favorite_movies)
}

sealed interface MovieListUiState {
    data class Success(val movies: List<Movie>): MovieListUiState
    object Error: MovieListUiState
    object Loading: MovieListUiState
}

sealed interface SelectedMovieUiState {
    data class Success(val movie: Movie,
                       val movieDetails: MovieDetails,
                       val movieReviews: List<MovieReview>,
                       val movieVideos: List<MovieVideo>,
                       val isFavorite: Boolean): SelectedMovieUiState
    object Error: SelectedMovieUiState
    object Loading: SelectedMovieUiState
}

class MemoremViewModel(private val moviesRepository: MoviesRepository,
                       private val savedMoviesRepository: SavedMoviesRepository) : ViewModel() {

    var movieListUiState: MovieListUiState by mutableStateOf(MovieListUiState.Loading)
        private set

    var selectedMovieUiState: SelectedMovieUiState by mutableStateOf(SelectedMovieUiState.Loading)
        private set

    init {
        getListMovies(MovieCategory.ALL_MOVIES)
    }

    fun getListMovies(category: MovieCategory) {
        viewModelScope.launch {
            movieListUiState = MovieListUiState.Loading
            movieListUiState = when(category) {
                MovieCategory.ALL_MOVIES -> {
                    try {
                        MovieListUiState
                            .Success(moviesRepository.getPopularMovies().results
                                    + moviesRepository.getTopRatedMovies().results)
                    } catch (e: IOException) {
                        MovieListUiState.Error
                    } catch (e: HttpException) {
                        MovieListUiState.Error
                    }
                }
                MovieCategory.POPULAR_MOVIES -> {
                    try {
                        MovieListUiState
                            .Success(moviesRepository.getPopularMovies().results)
                    } catch (e: IOException) {
                        MovieListUiState.Error
                    } catch (e: HttpException) {
                        MovieListUiState.Error
                    }
                }
                MovieCategory.TOP_RATED_MOVIES -> {
                    try {
                        MovieListUiState
                            .Success(moviesRepository.getTopRatedMovies().results)
                    } catch (e: IOException) {
                        MovieListUiState.Error
                    } catch (e: HttpException) {
                        MovieListUiState.Error
                    }
                }
                MovieCategory.FAVORITE_MOVIES -> {
                    try {
                        MovieListUiState
                            .Success(savedMoviesRepository.getSavedMovies())
                    } catch (e: IOException) {
                        MovieListUiState.Error
                    } catch (e: HttpException) {
                        MovieListUiState.Error
                    }
                }
            }
        }
    }

    fun getSavedMovies() {
        viewModelScope.launch {
            movieListUiState = MovieListUiState.Loading
            movieListUiState = try {
                MovieListUiState.Success(savedMoviesRepository.getSavedMovies())
            } catch (e: IOException) {
                MovieListUiState.Error
            } catch (e: HttpException) {
                MovieListUiState.Error
            }
        }
    }

    fun saveMovie(movie: Movie) {
        viewModelScope.launch {
            savedMoviesRepository.insertMovie(movie)
            selectedMovieUiState = SelectedMovieUiState.Success(
                moviesRepository.getMovie(movie.id),
                moviesRepository.getMovieDetails(movie.id),
                moviesRepository.getMovieReviews(movie.id).results,
                moviesRepository.getMovieVideos(movie.id).results,
                true)
        }
    }

    fun deleteMovie(movie: Movie) {
        viewModelScope.launch {
            savedMoviesRepository.deleteMovie(movie)
            selectedMovieUiState = SelectedMovieUiState.Success(
                moviesRepository.getMovie(movie.id),
                moviesRepository.getMovieDetails(movie.id),
                moviesRepository.getMovieReviews(movie.id).results,
                moviesRepository.getMovieVideos(movie.id).results,
                false)
        }
    }

    fun setSelectedMovieDetails(movie: Movie) {
        viewModelScope.launch {
            selectedMovieUiState = SelectedMovieUiState.Loading
            selectedMovieUiState = try {
                SelectedMovieUiState.Success(
                    moviesRepository.getMovie(movie.id),
                    moviesRepository.getMovieDetails(movie.id),
                    moviesRepository.getMovieReviews(movie.id).results,
                    moviesRepository.getMovieVideos(movie.id).results,
                    savedMoviesRepository.getMovie(movie.id) != null)
            } catch (e: IOException) {
                SelectedMovieUiState.Error
            } catch (e: HttpException) {
                SelectedMovieUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MemoremApplication)
                val moviesRepository = application.container.moviesRepository
                val savedMoviesRepository = application.container.savedMoviesRepository
                MemoremViewModel(moviesRepository = moviesRepository,
                    savedMoviesRepository = savedMoviesRepository)
            }
        }
    }
}