package com.example.kotlin_mvvm.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kotlin_mvvm.R
import com.example.kotlin_mvvm.model.Country

class ListFragmentAdapter(var countryList: ArrayList<Country>) :
    RecyclerView.Adapter<ListFragmentAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.ListText).text = countryList[position].name
        Glide.with(holder.itemView.context).load(countryList[position].flag).apply(RequestOptions().override(200,200))
            .into(holder.itemView.findViewById(R.id.ListImage))

    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateList(newList: ArrayList<Country>) {
        countryList.clear()
        countryList.addAll(newList)
        notifyDataSetChanged()
    }
}