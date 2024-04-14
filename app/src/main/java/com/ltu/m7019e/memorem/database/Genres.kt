package com.ltu.m7019e.memorem.database

import com.ltu.m7019e.memorem.model.Genre

class Genres {
    fun getGenres(): List<Genre> {
    return listOf(
            Genre(
            id = 0,
            name = "Action"
        ),
        Genre(
            id = 1,
            name = "Adventure"
        ),
        Genre(
            id = 2,
            name = "Animation"
        ),
        Genre(
            id = 3,
            name = "Comedy"
        ),
        Genre(
            id = 4,
            name = "Crime"
        ),
        Genre(
            id = 5,
            name = "Documentary"
        ),
        Genre(
            id = 6,
            "Drama"
        ),
        Genre(
            id = 7,
            name = "Family"
        ),
        Genre(
            id = 8,
            name = "Fantasy"
        ),
        Genre(
            id = 9,
            name = "History"
        ),
        Genre(
            id = 10,
            name = "Horror"
        ),
        Genre(
            id = 11,
            name = "Music"
        ),
        Genre(
            id = 12,
            name = "Mystery"
        ),
        Genre(
            id = 13,
            name = "Romance"
        ),
        Genre(
            id = 14,
            name = "Science Fiction"
        ),
        Genre(
            id = 15,
            name = "TV Movie"
        ),
        Genre(
            id = 16,
            name = "Thriller"
        ),
        Genre(
            id = 17,
            name = "War"
        ),
        Genre(
            id = 18,
            name = "Western"
        )
    ) }


}