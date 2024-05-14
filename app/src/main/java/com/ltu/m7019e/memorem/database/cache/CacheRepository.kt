package com.ltu.m7019e.memorem.database.cache

import android.content.Context
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.ltu.m7019e.memorem.database.MovieCacheDao
import com.ltu.m7019e.memorem.model.Movie
import com.ltu.m7019e.memorem.model.MovieDetails
import com.ltu.m7019e.memorem.model.MovieReview
import com.ltu.m7019e.memorem.utils.Constants.CHUNK_INDEX
import com.ltu.m7019e.memorem.utils.Constants.MAX_BYTES
import com.ltu.m7019e.memorem.utils.Constants.MOVIES_CACHE
import com.ltu.m7019e.memorem.utils.Constants.NB_CHUNKS
import com.ltu.m7019e.memorem.utils.Constants.UNIQUE
import com.ltu.m7019e.memorem.utils.MovieCacheConverter
import com.ltu.m7019e.memorem.viewmodel.MovieCategory
import com.ltu.m7019e.memorem.workers.CacheWorker
import com.ltu.m7019e.memorem.workers.GetCacheWorker
import kotlinx.coroutines.CompletableDeferred
import kotlin.math.ceil

class CacheRepository(private val movieCacheDao: MovieCacheDao, context: Context) : CachedMoviesRepository {
    private val workManager = WorkManager.getInstance(context)

    override suspend fun getCategory(): MovieCategory {
        return movieCacheDao.getCategory()
    }
    override suspend fun getMovies(): MovieCache {
        val getCacheBuilder = OneTimeWorkRequestBuilder<GetCacheWorker>().build()
        workManager.enqueue(getCacheBuilder)
        val resultDeferred = CompletableDeferred<MovieCache>()
        workManager.getWorkInfoByIdLiveData(getCacheBuilder.id)
            .observeForever { workInfo ->
                if (workInfo != null && workInfo.state.isFinished) {
                    val movieCacheJson = workInfo.outputData.getString(MOVIES_CACHE)
                    val movieCache =
                        if (movieCacheJson != null) {
                        MovieCacheConverter().fromString(movieCacheJson)
                    } else {
                        null
                    }
                    movieCache?.let { resultDeferred.complete(it) }
                }
            }
        return resultDeferred.await()
    }

    override suspend fun insertListMovies(movies: MovieCache) {
        val values = MovieCacheConverter().toString(movies)
        val chunks = ceil(values.length.toDouble() / MAX_BYTES).toInt()

        for(i in 0 until chunks) {
            val startIndex = i * MAX_BYTES
            val endIndex = Math.min((i + 1) * MAX_BYTES, values.length)
            val chunk = values.substring(startIndex, endIndex)

            val builder = Data
                .Builder()
                .putString(MOVIES_CACHE, chunk)
                .putInt(CHUNK_INDEX, i)
                .putInt(NB_CHUNKS, chunks)
                .build()

            val cacheBuilder = OneTimeWorkRequestBuilder<CacheWorker>()
                .setInputData(builder)
                .build()

            val workContinuation = workManager.beginUniqueWork(
                UNIQUE,
                ExistingWorkPolicy.REPLACE,
                cacheBuilder
            )


            workContinuation.enqueue()
        }
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