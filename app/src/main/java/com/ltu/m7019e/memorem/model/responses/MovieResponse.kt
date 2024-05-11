package com.ltu.m7019e.memorem.model.responses

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ltu.m7019e.memorem.model.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "cache_movies")
data class MovieResponse(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @SerialName(value = "page")
    var page: Int = 0,

    @SerialName(value = "results")
    var results: List<Movie> = listOf(),

    @SerialName(value = "total_pages")
    var totalPages: Int = 0,

    @SerialName(value = "total_results")
    var totalResults: Int = 0
)
