package com.alyssacuan.movlancer.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_table")
data class Movie(
    @PrimaryKey @ColumnInfo(name = "id") @SerializedName("id") val id: Int,
    @ColumnInfo(name = "title") @SerializedName("title") val title: String,
    @ColumnInfo(name = "vote_average") @SerializedName("vote_average") val voteAverage: Double,
    @ColumnInfo(name = "poster_path") @SerializedName("poster_path") val posterPath: String
)

data class Result (val page: Int, val total_results: Int, val total_pages: Int, val results: List<Movie>)