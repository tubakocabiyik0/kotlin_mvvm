package com.example.kotlin_mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.kotlin_mvvm.model.Country
import com.example.kotlin_mvvm.service.CountryDatabase
import com.example.kotlin_mvvm.service.RetrofitService
import com.example.kotlin_mvvm.util.CustomSharedPreferences
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFragmentViewModel(application: Application) : BaseViewModel(application = application) {
    var countryList = MutableLiveData<ArrayList<Country>>()
    var isLoading = MutableLiveData<Boolean>()
    var error = MutableLiveData<Boolean>()
    private var customSharedPreferences = CustomSharedPreferences(getApplication())


    @InternalCoroutinesApi
    fun getDataFromApi() {
        isLoading.value = true
        error.value = false
        var call = RetrofitService.getRetrofit().getData()
        call.enqueue(object : Callback<ArrayList<Country>> {
            override fun onResponse(
                call: Call<ArrayList<Country>>?,
                response: Response<ArrayList<Country>>?
            ) {
                response?.let {
                    System.out.println("fromApi")
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
    private fun saveSql(list: Response<ArrayList<Country>>) {
        launch {
            var database = CountryDatabase(getApplication())
            var dao = database.countryDao()
            //return list of uuid(long )
            var listLong = dao.addCountry(*list.body().toTypedArray())
            var i = 0

            while (i < list.body().size) {
                list.body()[i].uuid = listLong[i].toInt()
                i = i + 1
            }

        }
        customSharedPreferences.saveTime(System.nanoTime())
    }

    @InternalCoroutinesApi
    fun getDatasFromSql() {
        isLoading.value = true
        error.value = false
        launch {
            val countries = CountryDatabase(getApplication()).countryDao().getAllData()

            if (countries != null) {
                isLoading.value = false
                error.value = false
                countryList.value = countries as ArrayList<Country>
            } else {
                isLoading.value = false
                error.value = true
            }


        }
    }

    fun getTime(): Long? {
        return customSharedPreferences.getTime()
    }
}