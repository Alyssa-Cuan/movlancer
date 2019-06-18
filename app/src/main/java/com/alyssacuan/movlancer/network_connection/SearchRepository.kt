package com.alyssacuan.movlancer.network_connection

import com.alyssacuan.movlancer.models.Result
import com.alyssacuan.movlancer.network_connection.*


/**
 * Repository method to access search functionality of the api service
 */
class SearchRepository(val apiService: TmdbApiService) {

    fun searchUsers(): io.reactivex.Observable<Result> {
        return apiService.search()
    }

}