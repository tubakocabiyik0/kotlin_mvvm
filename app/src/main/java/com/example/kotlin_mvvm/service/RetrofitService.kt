package com.example.kotlin_mvvm.service

import com.example.kotlin_mvvm.util.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    fun getRetrofit(): RetrofitApi {
        var retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(RetrofitApi::class.java)
    }
}