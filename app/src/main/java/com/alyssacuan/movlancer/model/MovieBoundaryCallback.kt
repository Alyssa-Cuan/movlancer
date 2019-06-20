package com.alyssacuan.movlancer.model

import android.util.Log
import androidx.paging.PagedList
import com.alyssacuan.movlancer.app.MovieApplication.Companion.database
import com.alyssacuan.movlancer.utils.PagingRequestHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

class MovieBoundaryCallback :
    PagedList.BoundaryCallback<Movie>() {

    private val api = TmdbApiService.create()
    private val executor = Executors.newSingleThreadExecutor()
    private val helper = PagingRequestHelper(executor)
    private val PAGE_SIZE = 20
    private var pageNum : Int = 1

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        loadItem(PagingRequestHelper.RequestType.INITIAL)
    }

    override fun onItemAtEndLoaded(itemAtEnd: Movie) {
        super.onItemAtEndLoaded(itemAtEnd)

        executor.execute {
            val page = database.movieDao().getCount()
            pageNum =  page / PAGE_SIZE
            pageNum++
            Log.d("page", pageNum.toString())
        }

        loadItem(PagingRequestHelper.RequestType.AFTER)
    }

    private fun loadItem(requestType: PagingRequestHelper.RequestType){
        helper.runIfNotRunning(requestType) { helperCallback ->
            api.getPosts(page = pageNum)
                .enqueue(object : Callback<Result> {

                    override fun onFailure(call: Call<Result>?, t: Throwable) {
                        Log.e("MovieBoundaryCallback", "Failed to load data!")
                        helperCallback.recordFailure(t)
                    }

                    override fun onResponse(
                        call: Call<Result>?,
                        response: Response<Result>
                    ) {

                        val posts = response.body()?.results
                        executor.execute {
                            database.movieDao().insert(posts ?: listOf())
                            helperCallback.recordSuccess()
                        }
                    }
                })
        }
    }
}