package com.assignment.infosys.view.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.infosys.ConstantUtility
import com.assignment.infosys.R
import com.assignment.infosys.data.Row
import com.assignment.infosys.view.adapter.NewsListAdapter
import com.assignment.infosys.viewmodel.NewsViewModel
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {

    private lateinit var newsViewModel: NewsViewModel
    private var newsListData: List<Row> = emptyList()
    var refreshTimes = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViewNews.layoutManager = LinearLayoutManager(this)
        recyclerViewNews.setHasFixedSize(true)
        viewModelInit()
    }

    private fun partItemClicked(partItem: Row) {
        Toast.makeText(this, "Clicked: ${partItem.title}", Toast.LENGTH_LONG).show()
    }

    fun viewModelInit() {
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)

        makeNewsAPIRequest()
        makeNewsAPITitle()

        swipeContainer.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(this, R.color.purple_200)
        )
        swipeContainer.setColorSchemeColors(Color.WHITE)
        swipeContainer.setOnRefreshListener {
            refreshTimes = +refreshTimes + ConstantUtility.REFRESH_TIME
            swipeContainer.isRefreshing = false
        }
    }

    fun makeNewsAPIRequest() {
        newsViewModel.observeNewsResponce().observe(this, Observer {
            if (it != null) {
                GlobalScope.launch(Dispatchers.IO) {
                    newsListData = it
                    launch(Dispatchers.Main) {
                        progressBar.visibility = View.INVISIBLE
                        recyclerViewNews.adapter = NewsListAdapter(newsListData,this@MainActivity) { partItem: Row ->
                            partItemClicked(partItem)
                        }
                    }
                }
            }
        })
    }

    fun  makeNewsAPITitle() {
        newsViewModel.observeNewsTitleResponce().observe(this, Observer {
            if (it != null) {
                lifecycleScope.launch(Dispatchers.Main) {
                    progressBar.visibility = View.INVISIBLE
                    this@MainActivity.supportActionBar?.title = it
                }
            }
        })
    }
}