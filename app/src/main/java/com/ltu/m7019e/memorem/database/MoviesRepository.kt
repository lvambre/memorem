package com.ltu.m7019e.memorem.database

import com.ltu.m7019e.memorem.model.Movie
import com.ltu.m7019e.memorem.model.MovieDetails
import com.ltu.m7019e.memorem.model.MovieReview
import com.ltu.m7019e.memorem.model.responses.MovieResponse
import com.ltu.m7019e.memorem.model.responses.MovieReviewResponse
import com.ltu.m7019e.memorem.model.responses.MovieVideoResponse
import com.ltu.m7019e.memorem.network.MemoremApiService

interface MoviesRepository {
    suspend fun getPopularMovies(): MovieResponse
    suspend fun getTopRatedMovies(): MovieResponse
    suspend fun getMovie(id: Long): Movie
    suspend fun getMovieDetails(id: Long): MovieDetails
    suspend fun getMovieReviews(id: Long): MovieReviewResponse
    suspend fun getMovieVideos(id: Long): MovieVideoResponse
    suspend fun toMovieDetails(movies: List<Movie>): List<MovieDetails>
    suspend fun getAllReviews(movies: List<Movie>): Map<Long, List<MovieReview>>
}

class NetworkMoviesRepository(private val apiService: MemoremApiService) : MoviesRepository {
    override suspend fun getPopularMovies(): MovieResponse {
        return apiService.getPopularMovies()
    }

    override suspend fun getTopRatedMovies(): MovieResponse {
        return apiService.getTopRatedMovies()
    }

    override suspend fun getMovie(id: Long): Movie {
        return apiService.getMovie(id)
    }

    override suspend fun getMovieDetails(id: Long): MovieDetails {
        return apiService.getMovieDetails(id)
    }

    override suspend fun getMovieReviews(id: Long): MovieReviewResponse {
        return apiService.getMovieReviews(id)
    }

    override suspend fun getMovieVideos(id: Long): MovieVideoResponse {
        return apiService.getMovieVideos(id)
    }

    override suspend fun toMovieDetails(movies: List<Movie>): List<MovieDetails> {
        val moviesDetails:MutableList<MovieDetails> = mutableListOf()
        movies.forEach {
            moviesDetails.add(this.getMovieDetails(it.id))
        }
        return moviesDetails
    }

    override suspend fun getAllReviews(movies: List<Movie>): Map<Long, List<MovieReview>> {
        val moviesReviews: MutableMap<Long, List<MovieReview>> = mutableMapOf()
        movies.forEach { movie ->
            moviesReviews.put(movie.id, this.getMovieReviews(movie.id).results)
        }
        return moviesReviews
    }
}

interface SavedMoviesRepository {
    suspend fun getSavedMovies(): List<Movie>

    suspend fun insertMovie(movie: Movie)

    suspend fun getMovie(id: Long): Movie

    suspend fun deleteMovie(movie: Movie)
}

class FavoriteMoviesRepository(private val movieDao: MovieDao): SavedMoviesRepository {
    override suspend fun getSavedMovies(): List<Movie> {
        return movieDao.getFavoriteMovies()
    }

    override suspend fun insertMovie(movie: Movie) {
        movieDao.insertFavoriteMovie(movie)
    }

    override suspend fun getMovie(id: Long): Movie {
        return movieDao.getMovie(id)
    }

    override suspend fun deleteMovie(movie: Movie) {
        movieDao.deleteFavoriteMovie(movie.id)
    }
}