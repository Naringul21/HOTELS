package com.example.hotels.HOTELS.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotels.HOTELS.data.models.Hotels
import com.example.hotels.R
import com.squareup.picasso.Picasso

class RvHomeFragmentAdapter() : RecyclerView.Adapter<RvHomeFragmentAdapter.MyViewHolder>() {
    fun setListData(data: MutableList<Hotels>) {
        dataList = data
    }

    inner class MyViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val name: TextView = viewItem.findViewById(R.id.hotelName_homeFr)
        val location: TextView = viewItem.findViewById(R.id.hotelLocation_homeFr)
        val image: ImageView = viewItem.findViewById(R.id.hotel_image_homeFr)
        val price: TextView = viewItem.findViewById(R.id.hotelPrice_homeFr)
        val ratingBar: RatingBar = viewItem.findViewById(R.id.rBar)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_items_home_fragment, parent, false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val hotel: Hotels = dataList[position]
        holder.apply {
            ratingBar.rating = hotel.rating.toFloat()
            name.text = hotel.name
            location.text = hotel.location
            price.text = hotel.price
            Picasso.get().load(hotel.image).into(image)

        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    private var dataList = mutableListOf<Hotels>()
}





