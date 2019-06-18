package com.alyssacuan.movlancer.models

import android.widget.ImageView
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("poster_path") val posterPath: String
)

data class Result (val page: Int, val total_results: Int, val total_pages: Int, val results: List<Movie>)