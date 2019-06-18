package com.alyssacuan.movlancer

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.BaseAdapter

import com.alyssacuan.movlancer.models.Movie

import com.alyssacuan.movlancer.network_connection.SearchRepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var adapter: BaseAdapter? = null

    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var movieList : List<Movie> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

//        layoutManager = GridLayoutManager(this, 2)
//        recyclerView.layoutManager = layoutManager
//        var grid = GridItemDecoration(10,2)
//        recyclerView.addItemDecoration(grid)

        val repository = SearchRepositoryProvider.provideSearchRepository()

        compositeDisposable.add(
            repository.searchUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({ result ->
                    //Log.d("Result", "There are ${result.total_results} results")
                    movieList = result.results
                    //Log.d("Result", "size: ${movieList.size}")
                    adapter = GridAdapter(this, movieList)
                    recyclerView.adapter = adapter

                }, { error ->
                    error.printStackTrace()
                })

        )


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

}
