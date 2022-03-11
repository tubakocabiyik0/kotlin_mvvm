package com.example.kotlin_mvvm.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlin_mvvm.model.Country

@Dao
interface CountryDao {
    @Insert
    suspend fun addCountry(vararg country:Country) :List<Long>

    @Query(value = "SELECT * FROM country")
    suspend fun getAllData(): kotlin.collections.List<com.example.kotlin_mvvm.model.Country>

    @Query(value = "SELECT * FROM country Where uuid=:id")
    suspend fun getCountry( id:Int):Country




}