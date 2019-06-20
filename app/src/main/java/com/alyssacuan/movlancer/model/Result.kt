package com.alyssacuan.movlancer.model

data class Result (val page: Int,
                   val total_results: Int,
                   val total_pages: Int,
                   val results: List<Movie>)