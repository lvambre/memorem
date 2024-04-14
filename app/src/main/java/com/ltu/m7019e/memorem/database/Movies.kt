package com.ltu.m7019e.memorem.database

import com.ltu.m7019e.memorem.model.Movie

class Movies {
    fun getPopularMovies(): List<Movie> {
        return listOf(
            Movie(
                id = 693134,
                title = "Dune: Part Two",
                posterPath = "/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg",
                backdropPath = "/xOMo8BRK7PfcJv9JCnx7s5hj0PX.jpg",
                releaseDate = "2024-02-27",
                overview = "Follow the mythic journey of Paul Atreides as he unites with Chani and the Fremen while on a path of revenge against the conspirators who destroyed his family. Facing a choice between the love of his life and the fate of the known universe, Paul endeavors to prevent a terrible future only he can foresee.",
                genres = listOf(Genres().getGenres()[14], Genres().getGenres()[1]),
                homepage = "https://www.dunemovie.com",
                imdbId = "tt15239678"

            ),
            Movie(
                id = 359410,
                title = "Road House",
                posterPath = "/bXi6IQiQDHD00JFio5ZSZOeRSBh.jpg",
                backdropPath = "/oe7mWkvYhK4PLRNAVSvonzyUXNy.jpg",
                releaseDate = "2024-03-08",
                overview = "Ex-UFC fighter Dalton takes a job as a bouncer at a Florida Keys roadhouse, only to discover that this paradise is not all it seems.",
                genres = listOf(Genres().getGenres()[0], Genres().getGenres()[16]),
                homepage = "https://www.amazon.com/gp/video/detail/B0CH5YQPZQ",
                imdbId = "tt3359350"
            ),
            Movie(
                id = 634492,
                title = "Madame Web",
                posterPath = "/rULWuutDcN5NvtiZi4FRPzRYWSh.jpg",
                backdropPath = "/pwGmXVKUgKN13psUjlhC9zBcq1o.jpg",
                releaseDate = "2024-02-14",
                overview = "Forced to confront revelations about her past, paramedic Cassandra Webb forges a relationship with three young women destined for powerful futures...if they can all survive a deadly present.",
                genres = listOf(Genres().getGenres()[0], Genres().getGenres()[8]),
                homepage ="https://www.madameweb.movie",
                imdbId = "tt11057302"
            ),
            Movie(
                id = 935271,
                title = "After the Pandemic",
                posterPath = "/p1LbrdJ53dGfEhRopG71akfzOVu.jpg",
                backdropPath = "/9c0lHTXRqDBxeOToVzRu0GArSne.jpg",
                releaseDate = "2022-03-01",
                overview = "Set in a post-apocalyptic world where a global airborne pandemic has wiped out 90% of the Earth's population and only the young and immune have endured as scavengers. For Ellie and Quinn, the daily challenges to stay alive are compounded when they become hunted by the merciless Stalkers.",
                genres = listOf(Genres().getGenres()[0], Genres().getGenres()[14]),
                homepage = "",
                imdbId = "tt12774526"
            ),
            Movie(
                id = 1181548,
                title = "Heart of the Hunter",
                posterPath = "/n726fdyL1dGwt15bY7Nj3XOXc4Q.jpg",
                backdropPath = "/wUp0bUXaveR40ikBhDgWwNTijuD.jpg",
                releaseDate = "2024-03-28",
                overview = "A retired assassin is pulled back into action when his friend uncovers a dangerous conspiracy at the heart of the South African government.",
                genres = listOf(Genres().getGenres()[0], Genres().getGenres()[12], Genres().getGenres()[16]),
                homepage = "https://www.netflix.com/title/81579704",
                imdbId = "tt28943278"
            )
        )
    }

    fun getTopRatedMovies(): List<Movie> {
        return listOf(
            Movie(
                id = 389,
                title = "12 Angry Men",
                posterPath = "/ow3wq89wM8qd5X7hWKxiRfsFf9C.jpg",
                backdropPath = "/qqHQsStV6exghCM7zbObuYBiYxw.jpg",
                releaseDate = "1957-04-10",
                overview = "The defense and the prosecution have rested and the jury is filing into the jury room to decide if a young Spanish-American is guilty or innocent of murdering his father. What begins as an open and shut case soon becomes a mini-drama of each of the jurors' prejudices and preconceptions about the trial, the accused, and each other.",
                genres = listOf(Genres().getGenres()[6]),
                homepage = "",
                imdbId = "tt0050083"
            ),
            Movie(
                id = 155,
                title = "The Dark Knight",
                posterPath = "/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
                backdropPath = "/dqK9Hag1054tghRQSqLSfrkvQnA.jpg",
                releaseDate = "2008-07-16",
                overview = "Batman raises the stakes in his war on crime. With the help of Lt. Jim Gordon and District Attorney Harvey Dent, Batman sets out to dismantle the remaining criminal organizations that plague the streets. The partnership proves to be effective, but they soon find themselves prey to a reign of chaos unleashed by a rising criminal mastermind known to the terrified citizens of Gotham as the Joker.",
                genres = listOf(Genres().getGenres()[6], Genres().getGenres()[0], Genres().getGenres()[4], Genres().getGenres()[16]),
                homepage = "https://www.warnerbros.com/movies/dark-knight/",
                imdbId = "tt0468569"
            ),
            Movie(
                id = 680,
                title = "Pulp Fiction",
                posterPath = "/d5iIlFn5s0ImszYzBPb8JPIfbXD.jpg",
                backdropPath = "/suaEOtk1N1sgg2MTM7oZd2cfVp3.jpg",
                releaseDate = "1994-09-10",
                overview = "A burger-loving hit man, his philosophical partner, a drug-addled gangster's moll and a washed-up boxer converge in this sprawling, comedic crime caper. Their adventures unfurl in three stories that ingeniously trip back and forth in time.",
                genres = listOf(Genres().getGenres()[16], Genres().getGenres()[4]),
                homepage = "https://www.miramax.com/movie/pulp-fiction/",
                imdbId = "tt0110912"
            ),
            Movie(
                id = 429,
                title = "The Good, the Bad and the Ugly",
                posterPath = "/bX2xnavhMYjWDoZp1VM6VnU1xwe.jpg",
                backdropPath = "/Adrip2Jqzw56KeuV2nAxucKMNXA.jpg",
                releaseDate = "1966-12-22",
                overview = "While the Civil War rages on between the Union and the Confederacy, three men – a quiet loner, a ruthless hitman, and a Mexican bandit – comb the American Southwest in search of a strongbox containing $200,000 in stolen gold.",
                genres = listOf(Genres().getGenres()[18]),
                homepage = "http://www.mgm.com/#/our-titles/766/The-Good,-the-Bad-and-the-Ugly",
                imdbId = "tt0060196"
            ),
            Movie(
                id = 637,
                title = "Life Is Beautiful",
                posterPath = "/74hLDKjD5aGYOotO6esUVaeISa2.jpg",
                backdropPath = "/gavyCu1UaTaTNPsVaGXT6pe5u24.jpg",
                releaseDate = "1997-12-20",
                overview = "A touching story of an Italian book seller of Jewish ancestry who lives in his own little fairy tale. His creative and happy life would come to an abrupt halt when his entire family is deported to a concentration camp during World War II. While locked up he tries to convince his son that the whole thing is just a game.",
                genres = listOf(Genres().getGenres()[3], Genres().getGenres()[6]),
                homepage = "",
                imdbId = "tt0118799"
            )
        )
    }
}
