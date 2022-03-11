package com.example.kotlin_mvvm.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlin_mvvm.model.Country
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(entities = [Country::class], version = 1)
abstract class CountryDatabase() : RoomDatabase() {

    abstract fun countryDao(): CountryDao

    companion object {

        @Volatile
        private var dbInstance: CountryDatabase? = null
        private val lock = Any()

        @InternalCoroutinesApi
        operator fun invoke(context: Context) = dbInstance ?: synchronized(lock) {
            dbInstance ?: Room.databaseBuilder(context,CountryDatabase::class.java,"xxx").build().also {
                 dbInstance = it


            }
        }


        private fun createDb(context: Context) =  Room.databaseBuilder(context, CountryDatabase::class.java, "sss").allowMainThreadQueries().build()
    }

}