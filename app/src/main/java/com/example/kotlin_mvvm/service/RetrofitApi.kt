package com.example.kotlin_mvvm.service

import com.example.kotlin_mvvm.model.Country
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {
    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getData(): Call<ArrayList<Country>>

}