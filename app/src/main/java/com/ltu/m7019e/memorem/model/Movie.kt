package com.ltu.m7019e.memorem.model

data class Movie(
    var id: Long = 0L,
    var title: String,
    var posterPath: String,
    var backdropPath: String,
    var releaseDate: String,
    var overview: String,
    var genres: List<Genre>,
    var homepage: String,
    var imdbId: String
)
