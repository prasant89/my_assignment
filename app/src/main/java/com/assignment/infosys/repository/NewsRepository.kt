package com.assignment.infosys.repository

import com.assignment.infosys.data.NewsResponceJson
import com.assignment.infosys.data.Row
import com.assignment.infosys.network.NewsEndpoints
import com.assignment.infosys.network.ServiceBuilder
import com.assignment.infosys.network.SingleLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository() {
    var rowListData: SingleLiveData<List<Row>> = SingleLiveData()
    var title: SingleLiveData<String> = SingleLiveData()
    fun newsApiRequest():SingleLiveData<List<Row>> {
        val request = ServiceBuilder.buildService(NewsEndpoints::class.java)
        val call = request.getNewsDetails()

        call.enqueue(object : Callback<NewsResponceJson> {
            override fun onResponse(
                call: Call<NewsResponceJson>,
                response: Response<NewsResponceJson>
            ) {
                if (response.isSuccessful) {
                    (response.body()!!.rows)
                    rowListData.value = response.body()!!.rows
                    title.value = response.body()!!.title
                }
            }

            override fun onFailure(call: Call<NewsResponceJson>, t: Throwable) {

            }
        })
        return rowListData
    }

    fun newsApiRequestTitle():SingleLiveData<String> {
        val request = ServiceBuilder.buildService(NewsEndpoints::class.java)
        val call = request.getNewsDetails()

        call.enqueue(object : Callback<NewsResponceJson> {
            override fun onResponse(
                call: Call<NewsResponceJson>,
                response: Response<NewsResponceJson>
            ) {
                if (response.isSuccessful) {
                    title.value = response.body()!!.title
                }
            }

            override fun onFailure(call: Call<NewsResponceJson>, t: Throwable) {

            }
        })
        return title
    }

}