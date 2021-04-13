package com.assignment.infosys.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.assignment.infosys.data.Row
import com.assignment.infosys.network.SingleLiveData
import com.assignment.infosys.repository.NewsRepository

class NewsViewModel(application: Application) : AndroidViewModel(application) {
    private var repository = NewsRepository()
    fun observeNewsResponce(): SingleLiveData<List<Row>> {
        return repository.newsApiRequest()
    }

    fun observeNewsTitleResponce(): SingleLiveData<String> {
        return repository.newsApiRequestTitle()
    }
}