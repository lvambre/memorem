package com.ltu.m7019e.memorem.database.cache

import com.ltu.m7019e.memorem.model.Movie
import com.ltu.m7019e.memorem.model.MovieDetails
import com.ltu.m7019e.memorem.model.MovieReview
import com.ltu.m7019e.memorem.viewmodel.MovieCategory

interface CachedMoviesRepository {
    suspend fun getCategory(): MovieCategory
    suspend fun getMovies(): MovieCache
    suspend fun insertListMovies(movies: MovieCache)
    suspend fun getMovie(id: Long): Movie?
    suspend fun getMovieDetails(id: Long): MovieDetails?
    suspend fun getMoviesReviews(id: Long): List<MovieReview>?
}
