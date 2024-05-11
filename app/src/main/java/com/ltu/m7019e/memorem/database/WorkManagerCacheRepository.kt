package com.ltu.m7019e.memorem.database

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.ltu.m7019e.memorem.model.Movie
import com.ltu.m7019e.memorem.viewmodel.MovieCategory
import com.ltu.m7019e.memorem.workers.CacheWorker
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class WorkManagerCacheRepository(context: Context) {
    private val workManager = WorkManager.getInstance(context)

    fun cacheMovieList(category: MovieCategory, movies: List<Movie>) {
        val inputData = workDataOf(
            WorkManagerCacheRepository.KEY_CATEGORY to category.name,
            WorkManagerCacheRepository.KEY_MOVIES to Json.encodeToString(movies)
        )

        val saveMovieListRequest = OneTimeWorkRequestBuilder<CacheWorker>()
            .addTag(WorkManagerCacheRepository.SAVE_CACHE)
            .setInputData(inputData)
            .build()

        workManager.enqueue(saveMovieListRequest)
    }

    fun cancelWork() {
        workManager.cancelUniqueWork(SAVE_CACHE)
    }

    companion object {
        private const val SAVE_CACHE = "save_in_cache"
        private const val KEY_CATEGORY = "category"
        private const val KEY_MOVIES = "movies"
    }
}