package com.alyssacuan.movlancer.network_connection

object SearchRepositoryProvider {

    fun provideSearchRepository(): SearchRepository {
        return SearchRepository(TmdbApiService.Factory.create())
    }

}