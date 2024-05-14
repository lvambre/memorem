package com.ltu.m7019e.memorem.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.ltu.m7019e.memorem.database.MovieDatabase
import com.ltu.m7019e.memorem.utils.Constants.MOVIES_CACHE
import com.ltu.m7019e.memorem.utils.MovieCacheConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCacheWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val movieCacheDao = MovieDatabase.getDatabase(applicationContext).movieCacheDao()

        return withContext(Dispatchers.IO) {
            return@withContext try {
                val movieCacheJson = MovieCacheConverter().toString(movieCacheDao.getMovies())
                val movieCache = MovieCacheConverter().fromString(movieCacheJson)
                val output = workDataOf(MOVIES_CACHE to movieCache)
                Result.success(output)
            } catch (e: Exception) {
                Result.failure()
            }
        }
    }
}