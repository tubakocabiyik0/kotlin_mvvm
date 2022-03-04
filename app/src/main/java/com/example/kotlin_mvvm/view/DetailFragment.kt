package com.example.kotlin_mvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.kotlin_mvvm.R


class DetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_detail, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        var name = args?.getString("name").toString()

        var capital = args?.getString("capital").toString()
        var imageUrl = args?.getString("flag").toString()

        view.findViewById<TextView>(R.id.detailName).text = name
        view.findViewById<TextView>(R.id.detailCapital).text = capital
        Glide.with(this).load(imageUrl).into(view.findViewById<ImageView>(R.id.detailImage))

    }


}