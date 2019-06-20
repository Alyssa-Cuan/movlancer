package com.alyssacuan.movlancer.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.alyssacuan.movlancer.app.MovieApplication
import com.alyssacuan.movlancer.model.Movie
import com.alyssacuan.movlancer.model.MovieBoundaryCallback
import com.alyssacuan.movlancer.model.MovieDb


class MovieViewModel() : ViewModel(){

    private lateinit var liveData : LiveData<PagedList<Movie>>

    init {
        initializeList()
    }

    fun getLiveData() = liveData

    private fun initializeList() {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()

        liveData = initializedPagedListBuilder(config).build()


    }

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, Movie> {
        val livePageListBuilder = LivePagedListBuilder<Int, Movie>(
            MovieApplication.database.movieDao().getAll(),
            config)
        livePageListBuilder.setBoundaryCallback(MovieBoundaryCallback())
        return livePageListBuilder
    }
}