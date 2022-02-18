package com.example.kotlin_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_mvvm.view.ListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment:ListFragment= ListFragment()
        supportFragmentManager.beginTransaction().replace(R.id.mainFragment,fragment).commit()
    }
}