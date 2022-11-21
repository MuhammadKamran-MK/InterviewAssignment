package com.example.interviewassignment.API

import com.example.interviewassignment.Models.ApiList
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("/fact")
    suspend fun getData():Response<ApiList>
}