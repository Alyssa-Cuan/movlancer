package com.alyssacuan.movlancer.network_connection

import com.alyssacuan.movlancer.models.Result
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApiService {

    @GET("3/movie/popular")
    fun search(@Query("api_key") apiKey: String = "427d2d32ffd5b4ececa6315fc1519a70"): Observable<Result>

    /**
     * Companion object to create the TmdbApiService
     */
    companion object Factory {
        fun create(): TmdbApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.themoviedb.org/")
                .build()

            return retrofit.create(TmdbApiService::class.java);
        }
    }
}
