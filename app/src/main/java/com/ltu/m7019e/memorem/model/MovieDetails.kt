package com.ltu.m7019e.memorem.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetails(
    @SerialName(value = "id")
    var id: Long = 0L,

    @SerialName(value = "title")
    var title: String,

    @SerialName(value = "poster_path")
    var posterPath: String,

    @SerialName(value = "backdrop_path")
    var backdropPath: String?,

    @SerialName(value = "release_date")
    var releaseDate: String?,

    @SerialName(value = "overview")
    var overview: String?,

    @SerialName(value = "genres")
    var genres: List<Genre>?,

    @SerialName(value = "homepage")
    var homepage: String?,

    @SerialName(value = "imdb_id")
    var imdbId: String?,

    @SerialName(value = "production_companies")
    var productionCompanies: List<ProductionCompany>? = listOf()
)

@Serializable
data class ProductionCompany(
    @SerialName(value = "name")
    var name: String?
)

