package com.ltu.m7019e.memorem.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ltu.m7019e.memorem.database.CacheRepository
import com.ltu.m7019e.memorem.database.MovieDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CacheWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val database = MovieDatabase.getDatabase(applicationContext)
        val movieCacheDao = database.movieCacheDao()
        val moviesRepository = CacheRepository(movieCacheDao)

        return withContext(Dispatchers.IO) {
            return@withContext try {
                val movieResponse = moviesRepository.getMovies()
                movieCacheDao.clearCache()
                movieCacheDao.insertListMovies(movieResponse)
                Result.success()
            } catch (e: Exception) {
                Result.failure()
            }
        }
    }
}