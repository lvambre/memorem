package com.ltu.m7019e.memorem.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieReview(
    @SerialName(value = "author")
    var author: String,
    @SerialName("author_details")
    val authorDetails: AuthorDetails,
    @SerialName(value = "content")
    var content: String,
)

@Serializable
data class AuthorDetails(
    @SerialName(value = "username")
    var username: String,
    @SerialName(value = "rating")
    var rating: Double?
)