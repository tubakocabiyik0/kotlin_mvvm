package com.example.kotlin_mvvm.view

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_mvvm.R
import com.example.kotlin_mvvm.adapter.ListFragmentAdapter
import com.example.kotlin_mvvm.viewmodel.ListFragmentViewModel


class ListFragment : Fragment() {

    private lateinit var listViewModel: ListFragmentViewModel
    private var adapter = ListFragmentAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var recyvlerView = view.findViewById<RecyclerView>(R.id.recyclerId)
        recyvlerView.layoutManager = LinearLayoutManager(context)
        listViewModel = ViewModelProvider(this).get(ListFragmentViewModel::class.java)
        listViewModel.getData()
        recyvlerView.adapter = adapter
        liveDatas(recyvlerView,view)
    }

    private fun liveDatas(recyvlerView: RecyclerView, view: View) {
        listViewModel.countryList.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                adapter.updateList(it)
            }
        })

        listViewModel.isLoading.observe(viewLifecycleOwner, Observer {loading->

            loading?.let {
                if(it){
                 view.findViewById<ProgressBar>(R.id.progressBar).visibility=View.VISIBLE
                }else{
                    view.findViewById<ProgressBar>(R.id.progressBar).visibility=View.INVISIBLE
                }
            }
        })
        listViewModel.error.observe(viewLifecycleOwner, Observer { error->
            error?.let {
                if(it){
                    view.findViewById<TextView>(R.id.textView).visibility=View.VISIBLE
                    recyvlerView.visibility=View.INVISIBLE
                } else{
                    view.findViewById<TextView>(R.id.textView).visibility=View.INVISIBLE

                }
            }
        })
    }
}


