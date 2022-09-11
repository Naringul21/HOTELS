package com.example.hotels.HOTELS.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotels.HOTELS.data.db.Hotels
import com.example.hotels.HOTELS.utils.Navigator
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

        val hotel: Hotels= dataList[position]
        holder.apply {
            name.text=hotel.name
            location.text=hotel.location
            Picasso.get().load(hotel.image).into(image)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}


class RvHomeFragmentAdapter() :RecyclerView.Adapter<RvHomeFragmentAdapter.MyViewHolder2>(){
    fun setListData(data: MutableList<Hotels>){
        dataList=data

        val yourMutableList: MutableList<Hotels> = mutableListOf()
        yourMutableList.sortBy { it.price }
    }

    inner class MyViewHolder2(viewItem: View) : RecyclerView.ViewHolder(viewItem){
        val name: TextView =itemView.findViewById(R.id.hotelName_homeFr)
        val location: TextView =itemView.findViewById(R.id.hotelLocation_homeFr)
        val image: ImageView =itemView.findViewById(R.id.hotel_image_homeFr)
        val price: TextView =itemView.findViewById(R.id.hotelPrice_homeFr)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder2 {
        val itemView= LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_items_home_fragment, parent, false)
        return MyViewHolder2(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder2, position: Int) {
        val hotel2: Hotels=dataList[position]
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
}












class RvSeeAllListAdapter(val navigator: Navigator) :RecyclerView.Adapter<RvSeeAllListAdapter.MyViewHolder>(){
    fun setListData(data: MutableList<Hotels>){
        dataList=data

    }
    inner class MyViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem){
        val name: TextView =itemView.findViewById(R.id.hotelName_seeAll)
        var location: TextView =itemView.findViewById(R.id.hotelLocation_seeAll)
        var image: ImageView =itemView.findViewById(R.id.hotel_image_seeAll)
        val price: TextView=itemView.findViewById(R.id.hotelPrice_seeAll)
        fun setOnClick(hotels: Hotels){
            image.setOnClickListener {
                navigator.navigate(hotels)

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_see_all_list_items, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val hotel: Hotels= dataList[position]
        holder.apply {
            name.text=hotel.name
            location.text=hotel.location
            price.text=hotel.price
            Picasso.get().load(hotel.image).into(image)
        }
        holder.setOnClick(hotel)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

private var dataList= mutableListOf<Hotels>()





