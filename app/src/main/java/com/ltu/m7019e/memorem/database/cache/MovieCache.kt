package com.ltu.m7019e.memorem.database.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ltu.m7019e.memorem.model.Movie
import com.ltu.m7019e.memorem.model.MovieDetails
import com.ltu.m7019e.memorem.model.MovieReview
import com.ltu.m7019e.memorem.viewmodel.MovieCategory

@Entity(tableName = "cache_movies")
class MovieCache(
    @PrimaryKey
    val category: MovieCategory = MovieCategory.ALL_MOVIES,

    val movies: List<Movie> = listOf(),

    val moviesDetails: List<MovieDetails> = listOf(),

    val moviesReviews: Map<Long, List<MovieReview>> = mapOf()
)