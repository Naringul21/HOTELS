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

class RvHomeFragmentAdapter() : RecyclerView.Adapter<RvHomeFragmentAdapter.MyViewHolder>(){
    fun setListData(data: MutableList<Hotels>){
        dataList=data


    }

    inner class MyViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem){
        val name: TextView =itemView.findViewById(R.id.hotelName_homeFr)
        val location: TextView =itemView.findViewById(R.id.hotelLocation_homeFr)
        val image: ImageView =itemView.findViewById(R.id.hotel_image_homeFr)
        val price: TextView =itemView.findViewById(R.id.hotelPrice_homeFr)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_items_home_fragment, parent, false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val hotel2: Hotels =dataList[position]
        holder.apply {
            name.text=hotel2.name
            location.text=hotel2.location
            price.text=hotel2.price
            Picasso.get().load(hotel2.image).into(image)

        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    private var dataList = mutableListOf<Hotels>()
}




