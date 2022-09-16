package com.example.hotels.HOTELS.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotels.HOTELS.data.models.Hotels
import com.example.hotels.R
import com.squareup.picasso.Picasso

class RvLatestSearchAdapter() :RecyclerView.Adapter<RvLatestSearchAdapter.MyViewHolder>(){
    fun setListData(data: MutableList<Hotels>) {
        dataList = data

    }
    inner class MyViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem){
        val name: TextView =itemView.findViewById(R.id.hotelName)
        var location: TextView =itemView.findViewById(R.id.hotelLocation)
        var image: ImageView =itemView.findViewById(R.id.imageHotel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_latest_search_items, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val hotel: Hotels = dataList[position]
        holder.apply {
            name.text=hotel.name
            location.text=hotel.location
            Picasso.get().load(hotel.image).into(image)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    private var dataList = mutableListOf<Hotels>()
}


























