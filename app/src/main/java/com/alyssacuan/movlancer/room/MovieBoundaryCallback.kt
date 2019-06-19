package com.alyssacuan.movlancer.room

import android.util.Log
import androidx.paging.PagedList
import com.alyssacuan.movlancer.models.*
import com.alyssacuan.movlancer.retrofit.TmdbApiService
import com.alyssacuan.movlancer.utils.PagingRequestHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

class MovieBoundaryCallback(private val db: MovieDb) :
    PagedList.BoundaryCallback<Movie>() {

    private val api = TmdbApiService.create()
    private val executor = Executors.newSingleThreadExecutor()
    private val helper = PagingRequestHelper(executor)
    private val PAGE_SIZE = 20
    private var pageNum : Int = 1

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        //1
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) { helperCallback ->
            api.getPosts(page=pageNum)
                //2
                .enqueue(object : Callback<Result> {

                    override fun onFailure(call: Call<Result>?, t: Throwable) {
                        //3
                        Log.e("MovieBoundaryCallback", "Failed to load data!")
                        helperCallback.recordFailure(t)
                    }

                    override fun onResponse(
                        call: Call<Result>?,
                        response: Response<Result>
                    ) {
                        //4
                        val posts = response.body()?.results
                        executor.execute {
                            db.movieDao().insert(posts ?: listOf())
                            helperCallback.recordSuccess()
                        }
                    }
                })
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: Movie) {
        super.onItemAtEndLoaded(itemAtEnd)

        executor.execute {
            var page = db.movieDao().getCount()
            pageNum =  page / PAGE_SIZE
            pageNum++
            Log.d("page", pageNum.toString())
        }
        helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) { helperCallback ->
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
                            db.movieDao().insert(posts ?: listOf())
                            helperCallback.recordSuccess()
                        }
                    }
                })
        }

    }
}