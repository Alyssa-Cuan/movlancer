package com.alyssacuan.movlancer.network_connection

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.alyssacuan.movlancer.models.Movie
import com.alyssacuan.movlancer.models.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDataSource : PageKeyedDataSource<Int, Movie>() {

    private val api = TmdbApiService.create()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>) {

        api.getPosts(page = 1)
            .enqueue(object : Callback<Result> {

                override fun onFailure(call: Call<Result>?, t: Throwable?) {
                    Log.e("MovieDataSource", "Failed to fetch data!")
                }

                override fun onResponse(
                    call: Call<Result>?,
                    response: Response<Result>) {

                    val results = response.body()
                    //val redditPosts = results?. { it.data }
                    callback.onResult(results?.results ?: listOf(), null, results?.page)
                }
            })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        api.getPosts(page = params.key)
            .enqueue(object : Callback<Result> {

                override fun onFailure(call: Call<Result>?, t: Throwable?) {
                    Log.e("MovieDataSource", "Failed to fetch data!")
                }

                override fun onResponse(
                    call: Call<Result>?,
                    response: Response<Result>) {

                    val listing = response.body()
                    val items = listing?.results
                    callback.onResult(items ?: listOf(), params.key+1)
                }
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
