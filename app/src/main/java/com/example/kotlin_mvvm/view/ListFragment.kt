package com.example.kotlin_mvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_mvvm.R
import com.example.kotlin_mvvm.adapter.ListFragmentAdapter
import com.example.kotlin_mvvm.model.Country
import com.example.kotlin_mvvm.service.RetrofitService
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class ListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        print("yes")
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         var recyvlerView=view.findViewById<RecyclerView>(R.id.recyclerId)
         recyvlerView.layoutManager=LinearLayoutManager(context)
         var list = RetrofitService.getRetrofit().getData()

        list.enqueue(object : retrofit2.Callback<ArrayList<Country>>{
            override fun onResponse(
                call: Call<ArrayList<Country>>?,
                response: Response<ArrayList<Country>>?
            ) {

                  response.let {

                      print("hata"+it!!.body()[0].capital)
                      val adapter=ListFragmentAdapter(it!!.body())
                      recyvlerView.adapter=adapter
                  }

            }
            override fun onFailure(call: Call<ArrayList<Country>>?, t: Throwable?) {
              print("failure"+t.toString())
            }

        })
    }
}


