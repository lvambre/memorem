package com.ltu.m7019e.memorem.model.responses

import com.ltu.m7019e.memorem.model.MovieReview
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieReviewResponse(
    @SerialName(value = "id")
    var id: Long = 0L,

    @SerialName(value = "page")
    var page: Int = 0,

    @SerialName(value = "results")
    var results: List<MovieReview> = listOf(),

    @SerialName(value = "total_pages")
    var totalPages: Int = 0,

    @SerialName(value = "total_results")
    var totalResults: Int = 0
)