package com.ltu.m7019e.memorem.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ltu.m7019e.memorem.database.MovieDatabase
import com.ltu.m7019e.memorem.utils.Constants
import com.ltu.m7019e.memorem.utils.Constants.MOVIES_CACHE
import com.ltu.m7019e.memorem.utils.MovieCacheConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CacheWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val movieCacheDao = MovieDatabase.getDatabase(applicationContext).movieCacheDao()
        val numChunks = inputData.getInt(Constants.NB_CHUNKS, 0)

        return withContext(Dispatchers.IO) {
            return@withContext try {
                val resultsBuilder = StringBuilder()
                for(i in 0 until numChunks) {
                    val chunk = inputData.getString(MOVIES_CACHE + "_" + i)
                    resultsBuilder.append(chunk)
                }

                val movies = MovieCacheConverter().fromString(resultsBuilder.toString())

                movieCacheDao.clearCache()
                movieCacheDao.insertListMovies(movies)

                Result.success()

            } catch (e: Exception) {
                Result.failure()
            }
        }
    }
}