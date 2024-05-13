package com.ltu.m7019e.memorem.database.cache

import com.ltu.m7019e.memorem.database.MovieCacheDao
import com.ltu.m7019e.memorem.model.Movie
import com.ltu.m7019e.memorem.model.MovieDetails
import com.ltu.m7019e.memorem.model.MovieReview
import com.ltu.m7019e.memorem.viewmodel.MovieCategory

class CacheRepository(private val movieCacheDao: MovieCacheDao) : CachedMoviesRepository {

    override suspend fun getCategory(): MovieCategory {
        return movieCacheDao.getCategory()
    }
    override suspend fun getMovies(): MovieCache {
        return movieCacheDao.getMovies()
    }

    override suspend fun insertListMovies(movies: MovieCache) {
        movieCacheDao.insertListMovies(movies)
    }

    override suspend fun clearCache() {
        movieCacheDao.clearCache()
    }

    override suspend fun getMovie(id: Long): Movie? {
        return movieCacheDao.getMovies().movies.find {
            it.id == id
        }
    }

    override suspend fun getMovieDetails(id: Long): MovieDetails? {
        return movieCacheDao.getMovies().moviesDetails.find {
            it.id == id
        }
    }

    override suspend fun getMoviesReviews(id: Long): List<MovieReview>? {
        return movieCacheDao.getMovies().moviesReviews[id]
    }
}