package com.assignment.infosys.view.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.infosys.R
import com.assignment.infosys.data.Row
import com.assignment.infosys.view.adapter.NewsAdapter
import com.assignment.infosys.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var newsListadapter: NewsAdapter
    private lateinit var newsViewModel: NewsViewModel
    private var newsListData: List<Row> = emptyList()
    var refreshTimes = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModelInit()
    }

    fun viewModelInit() {
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        makeNewsAPIRequestForTitle()
        makeNewsAPIRequest()

        //** Set the colors of the Pull To Refresh View
        swipeContainer.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(this, R.color.purple_200))
        swipeContainer.setColorSchemeColors(Color.WHITE)
        swipeContainer.setOnRefreshListener {
            refreshTimes =+ refreshTimes + 10
            recyclerViewNews.adapter = newsListadapter
            swipeContainer.isRefreshing = false
        }
}
    //Call Webservice Api
    fun makeNewsAPIRequest() {
        newsViewModel.observeNewsResponce().observe(this, Observer {
            if (it != null) {
                GlobalScope.launch(Dispatchers.Main) {
                    progressBar.visibility = View.INVISIBLE
                    newsListData = it
                    newsListadapter = NewsAdapter(newsListData)
                    newsListadapter.notifyDataSetChanged()
                    setUpRecyclerView()
                }
            }
            Toast.makeText(applicationContext, "" + it.toString(), Toast.LENGTH_SHORT).show()
        })
    }

    //Call Webservice Api
    fun makeNewsAPIRequestForTitle() {
        newsViewModel.observeNewsTitleResponce().observe(this, Observer {
            if (it != null) {
                GlobalScope.launch(Dispatchers.Main) {
                    progressBar.visibility = View.INVISIBLE
                    this@MainActivity.supportActionBar?.title = ""+it
                }
            }
            Toast.makeText(applicationContext, "" + it.toString(), Toast.LENGTH_SHORT).show()
        })
    }

    //Bind RecyclerView
    private fun setUpRecyclerView() {
        recyclerViewNews!!.layoutManager = LinearLayoutManager(this)
        recyclerViewNews!!.adapter = newsListadapter
        recyclerViewNews!!.itemAnimator = DefaultItemAnimator()
        recyclerViewNews!!.isNestedScrollingEnabled = true
    }
}