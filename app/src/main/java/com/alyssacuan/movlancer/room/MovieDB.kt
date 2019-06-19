package com.alyssacuan.movlancer.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alyssacuan.movlancer.models.Movie

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDb : RoomDatabase() {

    companion object {
        fun create(context: Context): MovieDb {
            val databaseBuilder = Room.databaseBuilder(context, MovieDb::class.java, "movie.db")
            return databaseBuilder.build()
        }
    }
    abstract fun movieDao(): MovieDao

}