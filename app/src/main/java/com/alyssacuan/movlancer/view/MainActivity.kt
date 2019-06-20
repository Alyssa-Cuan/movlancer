package com.alyssacuan.movlancer.view

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alyssacuan.movlancer.R
import com.alyssacuan.movlancer.app.MovieApplication.Companion.database
import com.alyssacuan.movlancer.model.Movie
import com.alyssacuan.movlancer.model.MovieBoundaryCallback
import com.alyssacuan.movlancer.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MovieViewModel

    private var adapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        configureUI()
        configureAdapters()
        configureObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    private fun configureUI(){
        val movColor = ContextCompat.getColor(this, R.color.movColor)
        val lancerColor = ContextCompat.getColor(this, R.color.lancerColor)
        val actionBarColor = ContextCompat.getColor(this, R.color.appBarColor)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(actionBarColor))
        supportActionBar?.setTitle(Html.fromHtml("<font color=${movColor}>${getString(R.string.app_firstname)}</font><font color=${lancerColor}>${getString(
            R.string.app_lastname)}</font>"))
    }

    private fun configureAdapters(){
        recyclerView.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    private fun configureObservers(){
        viewModel.getLiveData().observe(this, Observer<PagedList<Movie>> { pagedList ->
            adapter.submitList(pagedList)
            var list : List<Movie> = pagedList.snapshot()

            Log.d("live", list[0].popularity.toString())
        })
    }

}
