package com.ltu.m7019e.memorem.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ltu.m7019e.memorem.database.cache.MovieCache
import com.ltu.m7019e.memorem.model.Movie
import com.ltu.m7019e.memorem.utils.MovieDetailsListConverter
import com.ltu.m7019e.memorem.utils.MovieListConverter
import com.ltu.m7019e.memorem.utils.MovieReviewMapConverter

@Database(entities = [Movie::class, MovieCache::class], version = 3, exportSchema = false)
@TypeConverters(MovieListConverter::class, MovieDetailsListConverter::class, MovieReviewMapConverter::class)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieCacheDao(): MovieCacheDao

    companion object {
        @Volatile
        private var Instance: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MovieDatabase::class.java, "movie_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}