package com.ltu.m7019e.memorem.database

import com.ltu.m7019e.memorem.model.Movie

class Movies {
    fun getPopularMovies(): List<Movie> {
        return listOf(
            Movie(
                id = 693134,
                title = "Dune: Part Two",
                posterPath = "/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg"
            ),
            Movie(
                id = 359410,
                title = "Road House",
                posterPath = "/bXi6IQiQDHD00JFio5ZSZOeRSBh.jpg"
            ),
            Movie(
                id = 634492,
                title = "Madame Web",
                posterPath = "/rULWuutDcN5NvtiZi4FRPzRYWSh.jpg"
            ),
            Movie(
                id = 935271,
                title = "After the Pandemic",
                posterPath = "/p1LbrdJ53dGfEhRopG71akfzOVu.jpg"
            ),
            Movie(
                id = 1181548,
                title = "Heart of the Hunter",
                posterPath = "/n726fdyL1dGwt15bY7Nj3XOXc4Q.jpg"
            )
        )
    }

    fun getTopRatedMovies(): List<Movie> {
        return listOf(
            Movie(
                id = 389,
                title = "12 Angry Men",
                posterPath = "/ow3wq89wM8qd5X7hWKxiRfsFf9C.jpg"
            ),
            Movie(
                id = 155,
                title = "The Dark Knight",
                posterPath = "/qJ2tW6WMUDux911r6m7haRef0WH.jpg"
            ),
            Movie(
                id = 680,
                title = "Pulp Fiction",
                posterPath = "/d5iIlFn5s0ImszYzBPb8JPIfbXD.jpg"
            ),
            Movie(
                id = 429,
                title = "The Good, the Bad and the Ugly",
                posterPath = "/bX2xnavhMYjWDoZp1VM6VnU1xwe.jpg"
            ),
            Movie(
                id = 637,
                title = "Life Is Beautiful",
                posterPath = "/74hLDKjD5aGYOotO6esUVaeISa2.jpg"
            )
        )
    }
}
