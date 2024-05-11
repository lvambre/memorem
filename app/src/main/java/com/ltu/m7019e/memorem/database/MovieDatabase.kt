package com.ltu.m7019e.memorem.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ltu.m7019e.memorem.model.Movie
import com.ltu.m7019e.memorem.model.responses.MovieResponse
import com.ltu.m7019e.memorem.utils.MovieListConverter

@Database(entities = [Movie::class, MovieResponse::class], version = 1, exportSchema = false)
@TypeConverters(MovieListConverter::class)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieCacheDao(): MovieCacheDao

    companion object {
        @Volatile
        private var Instance: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MovieDatabase::class.java, "movie_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}