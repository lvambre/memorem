package com.ltu.m7019e.memorem.network

import com.ltu.m7019e.memorem.model.MovieResponse
import com.ltu.m7019e.memorem.utils.Constants
import retrofit2.http.GET
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
}