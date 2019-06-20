package com.alyssacuan.movlancer.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


//@Entity
//data class Movie(
//    @PrimaryKey @ColumnInfo(name = "id") @SerializedName("id") val id: Int,
//    @ColumnInfo(name = "title") @SerializedName("title") val title: String,
//    @ColumnInfo(name = "vote_average") @SerializedName("vote_average") val voteAverage: Double,
//    @ColumnInfo(name = "poster_path") @SerializedName("poster_path") val posterPath: String,
//    @ColumnInfo(name = "popularity") @SerializedName("popularity") val popularity: Double
//)
//
//data class Result (val page: Int, val total_results: Int, val total_pages: Int, val results: List<Movie>)