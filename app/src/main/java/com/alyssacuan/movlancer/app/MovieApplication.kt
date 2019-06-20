package com.alyssacuan.movlancer.app

import android.app.Application
import androidx.room.Room
import com.alyssacuan.movlancer.model.MovieDb

class MovieApplication : Application(){
    companion object{
        lateinit var database : MovieDb
    }

    override fun onCreate() {
        super.onCreate()
        database = MovieDb.create(this)
    }
}