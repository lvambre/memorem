package com.ltu.m7019e.memorem.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ltu.m7019e.memorem.model.responses.MovieResponse

@Dao
interface MovieCacheDao {
    @Query("SELECT * FROM cache_movies")
    suspend fun getMovies(): MovieResponse

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertListMovies(movies: MovieResponse)

    @Query("DELETE FROM cache_movies")
    suspend fun clearCache()
}