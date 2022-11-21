package com.example.interviewassignment.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.interviewassignment.API.ApiInterface
import com.example.interviewassignment.Models.ApiList

class MyRepository(private val apiInterface: ApiInterface) {

    private val mLiveData = MutableLiveData<ApiList>()

    val mData : LiveData<ApiList>
    get() = mLiveData

    suspend fun getMemes() {
        val result = apiInterface.getData()

        if (result.body() != null) {
            mLiveData.postValue(result.body())
        }
    }

}