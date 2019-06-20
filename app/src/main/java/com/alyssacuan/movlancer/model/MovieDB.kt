package com.alyssacuan.movlancer.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

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