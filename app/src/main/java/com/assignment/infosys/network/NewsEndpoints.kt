package com.assignment.infosys.network

import com.assignment.infosys.data.NewsResponceJson
import retrofit2.Call
import retrofit2.http.GET

interface NewsEndpoints {
    @GET("/s/2iodh4vg0eortkl/facts.json")
    fun getNewsDetails(): Call<NewsResponceJson>
}