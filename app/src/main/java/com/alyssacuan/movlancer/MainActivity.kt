package com.alyssacuan.movlancer

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alyssacuan.movlancer.models.Movie
import com.alyssacuan.movlancer.room.MovieBoundaryCallback
import com.alyssacuan.movlancer.room.MovieDb
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var adapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        var movColor = ContextCompat.getColor(this, R.color.movColor)
        var lancerColor = ContextCompat.getColor(this, R.color.lancerColor)
        var actionBarColor = ContextCompat.getColor(this, R.color.appBarColor)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(actionBarColor))
        getSupportActionBar()?.setTitle(Html.fromHtml("<font color=${movColor}>${getString(R.string.app_firstname)}</font><font color=${lancerColor}>${getString(R.string.app_lastname)}</font>"))
        initializeList()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    private fun initializeList() {

        recyclerView.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter

        //1
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()

        //2
        val liveData = initializedPagedListBuilder(config).build()

        //3
        liveData.observe(this, Observer<PagedList<Movie>> { pagedList ->
            adapter.submitList(pagedList)
        })

    }

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, Movie> {

        val database = MovieDb.create(this)
        val livePageListBuilder = LivePagedListBuilder<Int, Movie>(
            database.movieDao().getAll(),
            config)
        livePageListBuilder.setBoundaryCallback(MovieBoundaryCallback(database))
        return livePageListBuilder
    }



}
