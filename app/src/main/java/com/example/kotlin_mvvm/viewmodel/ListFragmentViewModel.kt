package com.example.kotlin_mvvm.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_mvvm.model.Country
import com.example.kotlin_mvvm.service.CountryDatabase
import com.example.kotlin_mvvm.service.RetrofitService
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFragmentViewModel(application: Application) : BaseViewModel(application = application) {
    var countryList = MutableLiveData<ArrayList<Country>>()
    var isLoading = MutableLiveData<Boolean>()
    var error = MutableLiveData<Boolean>()


    @InternalCoroutinesApi
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

                    saveSql(it)
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
    @InternalCoroutinesApi
    private fun saveSql(list: Response<ArrayList<Country>>){
     launch {
         var dao=CountryDatabase(getApplication()).countryDao()
         //return list of uuid(long )
         var listLong=dao.addCountry(*list.body().toTypedArray())
         var i=0

         while (i<list.body().size){
          list.body()[i].uuid=listLong[i].toInt()
             i=+1
         }

     }
    }

}