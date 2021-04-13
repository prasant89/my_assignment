package com.assignment.infosys.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.action.Swiper
import com.assignment.infosys.data.Row
import com.assignment.infosys.network.SingleLiveData
import com.assignment.infosys.repository.NewsRepository
import com.bumptech.glide.load.engine.Resource
import junit.framework.TestCase
import androidx.lifecycle.Observer
import com.assignment.infosys.data.NewsResponceJson
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import kotlin.collections.List as List

@RunWith(MockitoJUnitRunner::class)
class NewsViewModelTest: TestCase() {
    private lateinit var viewModel: NewsViewModel
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var newsRepo: NewsRepository
    @Mock
    private lateinit var newsObserver: Observer<Resource<List<Row>>>
    @Captor
    private lateinit var argumentCaptor:ArgumentCaptor<Resource<List<Row>>>

    @Mock
    private lateinit var titleObserver: Observer<Resource<List<String>>>
    @Captor
    private lateinit var titleArgumentCaptor:ArgumentCaptor<Resource<List<String>>>

    @get:Rule
     val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun observeNewsResponce(){
        Mockito.`when`(newsRepo.newsApiRequest())
            .thenReturn(SingleLiveData.equals(argumentCaptor) as SingleLiveData<List<Row>>)
        Mockito.verify(newsObserver, Mockito.times(1))
            .onChanged(argumentCaptor.capture())
        viewModel.observeNewsResponce().observeForever(newsObserver as Observer<in List<Row>>)
        val values = argumentCaptor.getAllValues();
        Assert.assertEquals("description",values.get(0))
    }

    @Test
    fun observeNewsTitleResponce(){
        Mockito.`when`(newsRepo.newsApiRequestTitle())
            .thenReturn(SingleLiveData.equals(titleArgumentCaptor) as SingleLiveData<String>)
        Mockito.verify(titleObserver, Mockito.times(1))
            .onChanged(titleArgumentCaptor.capture())
        viewModel.observeNewsTitleResponce().observeForever(titleObserver as Observer<in String>)
        val values = argumentCaptor.getAllValues();
        Assert.assertEquals("title",values.get(0))
    }
}


