package com.ltu.m7019e.memorem.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ltu.m7019e.memorem.database.cache.MovieCache
import com.ltu.m7019e.memorem.viewmodel.MovieCategory

@Dao
interface MovieCacheDao {
    @Query("SELECT category FROM cache_movies")
    suspend fun getCategory(): MovieCategory
    @Query("SELECT * FROM cache_movies")
    suspend fun getMovies(): MovieCache

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertListMovies(movies: MovieCache)

    @Query("DELETE FROM cache_movies")
    suspend fun clearCache()
}