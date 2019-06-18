package com.alyssacuan.movlancer

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.Menu
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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

        var movColor = ContextCompat.getColor(this, R.color.movColor)
        var lancerColor = ContextCompat.getColor(this, R.color.lancerColor)
        var actionBarColor = ContextCompat.getColor(this, R.color.appBarColor)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(actionBarColor))
        getSupportActionBar()?.setTitle(Html.fromHtml("<font color=${movColor}>${getString(R.string.app_firstname)}</font><font color=${lancerColor}>${getString(R.string.app_lastname)}</font>"))


        val repository = SearchRepositoryProvider.provideSearchRepository()

        compositeDisposable.add(
            repository.searchUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({ result ->
                    Log.d("Result", "There are ${result.total_results} results")
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
