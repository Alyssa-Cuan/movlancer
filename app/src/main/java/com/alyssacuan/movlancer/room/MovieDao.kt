package com.alyssacuan.movlancer.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alyssacuan.movlancer.models.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts : List<Movie>)

    @Query("SELECT * FROM Movie ORDER BY popularity DESC")
    fun getAll() : DataSource.Factory<Int, Movie>

    @Query("SELECT COUNT(*) FROM Movie")
    fun getCount(): Int
}