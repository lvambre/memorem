package com.ltu.m7019e.memorem.network

import com.ltu.m7019e.memorem.model.MovieDetails
import com.ltu.m7019e.memorem.model.MovieResponse
import com.ltu.m7019e.memorem.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MemoremApiService {

    @GET("popular")
    suspend fun getPopularMovies(
        @Query("api_key")
        apiKey: String = Constants.API_KEY
    ): MovieResponse
    @GET("top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key")
        apiKey: String = Constants.API_KEY
    ): MovieResponse

    @GET("{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): MovieDetails
}