package com.example.kotlin_mvvm.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(application: Application) : AndroidViewModel(application),CoroutineScope {
     private val job=Job()

    override val coroutineContext: CoroutineContext
        //job 'u yap ve main threade geçiş yap
        get() = job+ Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}