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
    @SerialName(value = "created_at")
    var createdAt: String,
    @SerialName(value = "url")
    var url: String
)

@Serializable
data class AuthorDetails(
    @SerialName(value = "username")
    var username: String,
    @SerialName(value = "avatar_path")
    var avatarPath: String?,
    @SerialName(value = "rating")
    var rating: Double?
)