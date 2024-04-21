package com.ltu.m7019e.memorem.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieVideo(
    @SerialName(value = "name")
    var name: String,

    @SerialName(value = "key")
    var key: String,

    @SerialName(value = "site")
    var site: String,

    @SerialName(value = "type")
    var type: String,

    @SerialName(value = "id")
    var id: String
)