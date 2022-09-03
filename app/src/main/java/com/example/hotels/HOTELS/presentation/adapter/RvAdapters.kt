package com.example.hotels.HOTELS.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotels.HOTELS.data.db.HotelsHomeFragment
import com.example.hotels.HOTELS.data.db.hotelLatestSearching
import com.example.hotels.R
import com.squareup.picasso.Picasso

class rvLatestSearchAdapter(private val hotelList: ArrayList<hotelLatestSearching>) :RecyclerView.Adapter<rvLatestSearchAdapter.MyViewHolder>(){

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

        val hotel: hotelLatestSearching=hotelList[position]
        holder.apply {
            name.text=hotel.name
            location.text=hotel.location
            Picasso.get().load(hotel.image).into(image)
        }
    }

    override fun getItemCount(): Int {
        return hotelList.size
    }
}


class rvHomeFragmentAdapter(private val hotelList2: ArrayList<HotelsHomeFragment>) :RecyclerView.Adapter<rvHomeFragmentAdapter.MyViewHolder2>(){
    inner class MyViewHolder2(viewItem: View) : RecyclerView.ViewHolder(viewItem){
        val name: TextView =itemView.findViewById(R.id.hotelName_homeFr)
        var location: TextView =itemView.findViewById(R.id.hotelLocation_homeFr)
        var image: ImageView =itemView.findViewById(R.id.hotel_image_homeFr)
        var price: TextView =itemView.findViewById(R.id.hotelPrice_homeFr)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder2 {
        val itemView= LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_items_home_fragment, parent, false)
        return MyViewHolder2(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder2, position: Int) {
        val hotel2: HotelsHomeFragment=hotelList2[position]
        holder.apply {
            name.text=hotel2.name
            location.text=hotel2.location
            price.text=hotel2.price
            Picasso.get().load(hotel2.image).into(image)
        }
    }

    override fun getItemCount(): Int {
       return hotelList2.size
    }
}

