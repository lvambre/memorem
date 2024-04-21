package com.ltu.m7019e.memorem.model.responses

import com.ltu.m7019e.memorem.model.MovieVideo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieVideoResponse(
    @SerialName(value = "id")
    var id: Long = 0L,

    @SerialName(value = "results")
    var results: List<MovieVideo> = listOf()
)