package com.ltu.m7019e.memorem.model

data class MovieDetails(
    var backdropPath: String,
    var releaseDate: String,
    var overview: String,
    var genres: List<Genre>,
    var homepage: String,
    var imdbId: String
)