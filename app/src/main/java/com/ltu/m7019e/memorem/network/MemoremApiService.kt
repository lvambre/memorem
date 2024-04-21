package com.ltu.m7019e.memorem.network

import com.ltu.m7019e.memorem.model.MovieDetails
import com.ltu.m7019e.memorem.model.responses.MovieResponse
import com.ltu.m7019e.memorem.model.responses.MovieReviewResponse
import com.ltu.m7019e.memorem.model.responses.MovieVideoResponse
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

    @GET("{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): MovieReviewResponse

    @GET("{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ) : MovieVideoResponse
}