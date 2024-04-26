package com.ltu.m7019e.memorem.model.responses

import com.ltu.m7019e.memorem.model.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    @SerialName(value = "page")
    var page: Int = 0,

    @SerialName(value = "results")
    var results: List<Movie> = listOf(),

    @SerialName(value = "total_pages")
    var totalPages: Int = 0,

    @SerialName(value = "total_results")
    var totalResults: Int = 0
)