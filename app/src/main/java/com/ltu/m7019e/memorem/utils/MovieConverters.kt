package com.ltu.m7019e.memorem.utils

import androidx.room.TypeConverter
import com.ltu.m7019e.memorem.model.Movie
import com.ltu.m7019e.memorem.model.MovieDetails
import com.ltu.m7019e.memorem.model.MovieReview
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MovieListConverter {
    private val json = Json

    @TypeConverter
    fun fromString(value: String): List<Movie> {
        return json.decodeFromString(value)
    }

    @TypeConverter
    fun toString(list: List<Movie>): String {
        return json.encodeToString(list)
    }
}

class MovieDetailsListConverter {
    private val json = Json

    @TypeConverter
    fun fromString(value: String): List<MovieDetails> {
        return json.decodeFromString(value)
    }

    @TypeConverter
    fun toString(list: List<MovieDetails>): String {
        return json.encodeToString(list)
    }
}

class MovieReviewMapConverter {
    private val json = Json

    @TypeConverter
    fun fromString(value: String): Map<Long, List<MovieReview>> {
        return json.decodeFromString(value)
    }

    @TypeConverter
    fun toString(map: Map<Long, List<MovieReview>>): String {
        return json.encodeToString(map)
    }
}
