package com.alyssacuan.movlancer.models

import android.widget.ImageView

data class Movie(val title: String, val imageUrl: String, val rating: Int)

data class Result (val total_count: Int, val incomplete_results: Boolean, val items: List<Movie>)