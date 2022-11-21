package com.example.interviewassignment.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewassignment.Models.ApiList
import com.example.interviewassignment.Repository.MyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel (private val myRepository: MyRepository) : ViewModel() {

    init {
        viewModelScope.launch (Dispatchers.IO) {
            myRepository.getMemes()
        }
    }

    val data : LiveData<ApiList>
    get() = myRepository.mData

}