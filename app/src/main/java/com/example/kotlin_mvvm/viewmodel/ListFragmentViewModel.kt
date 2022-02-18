package com.example.kotlin_mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_mvvm.model.Country
import com.example.kotlin_mvvm.service.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFragmentViewModel : ViewModel() {
    var countryList = MutableLiveData<ArrayList<Country>>()
    var isLoading = MutableLiveData<Boolean>()
    var error = MutableLiveData<Boolean>()


    fun getData() {
        isLoading.value = true
        error.value = false
        var call = RetrofitService.getRetrofit().getData()
        call.enqueue(object : Callback<ArrayList<Country>> {
            override fun onResponse(
                call: Call<ArrayList<Country>>?,
                response: Response<ArrayList<Country>>?
            ) {
                response?.let {
                    isLoading.value = false
                    error.value = false
                    countryList.value = it.body()

                }
            }

            override fun onFailure(call: Call<ArrayList<Country>>?, t: Throwable?) {
                isLoading.value = false
                error.value = true
            }

        })
    }


}