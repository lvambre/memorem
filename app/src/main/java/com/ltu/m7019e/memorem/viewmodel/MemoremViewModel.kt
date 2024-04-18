package com.ltu.m7019e.memorem.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ltu.m7019e.memorem.MemoremApplication
import com.ltu.m7019e.memorem.database.MoviesRepository
import com.ltu.m7019e.memorem.model.Movie
import com.ltu.m7019e.memorem.model.MovieDetails
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MovieListUiState {
    data class Success(val movies: List<Movie>): MovieListUiState
    object Error: MovieListUiState
    object Loading: MovieListUiState
}

sealed interface SelectedMovieUiState {
    data class Success(val movie: Movie): SelectedMovieUiState
    object Error: SelectedMovieUiState
    object Loading: SelectedMovieUiState
}

sealed interface SelectedMovieDetailsUiState {
    data class Success(val movieDetails: MovieDetails): SelectedMovieDetailsUiState
    object Error: SelectedMovieDetailsUiState
    object Loading: SelectedMovieDetailsUiState
}
class MemoremViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    var movieListUiState: MovieListUiState by mutableStateOf(MovieListUiState.Loading)
        private set

    var selectedMovieUiState: SelectedMovieUiState by mutableStateOf(SelectedMovieUiState.Loading)
        private set

    var selectedMovieDetailsUiState: SelectedMovieDetailsUiState by mutableStateOf(SelectedMovieDetailsUiState.Loading)

    init {
        getPopularMovies()
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            movieListUiState = MovieListUiState.Loading
            movieListUiState = try {
                MovieListUiState.Success(moviesRepository.getTopRatedMovies().results)
            } catch (e: IOException) {
                MovieListUiState.Error
            } catch (e: HttpException) {
                MovieListUiState.Error
            }
        }
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            movieListUiState = MovieListUiState.Loading
            movieListUiState = try {
                MovieListUiState.Success(moviesRepository.getPopularMovies().results)
            } catch (e: IOException) {
                MovieListUiState.Error
            } catch (e: HttpException) {
                MovieListUiState.Error
            }
        }
    }

    fun setSelectedMovie(movie: Movie) {
        viewModelScope.launch {
            selectedMovieUiState = SelectedMovieUiState.Loading
            selectedMovieUiState = try {
                SelectedMovieUiState.Success(movie)
            } catch (e: IOException) {
                SelectedMovieUiState.Error
            } catch (e: HttpException) {
                SelectedMovieUiState.Error
            }
        }
    }

    fun getMovieDetails(id: Long) {
        viewModelScope.launch {
            selectedMovieDetailsUiState = SelectedMovieDetailsUiState.Loading
            selectedMovieDetailsUiState = try {
                SelectedMovieDetailsUiState.Success(moviesRepository.getMovieDetails(id))
            } catch (e: IOException) {
                SelectedMovieDetailsUiState.Error
            } catch (e: HttpException) {
                SelectedMovieDetailsUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MemoremApplication)
                val moviesRepository = application.container.moviesRepository
                MemoremViewModel(moviesRepository = moviesRepository)
            }
        }
    }
}