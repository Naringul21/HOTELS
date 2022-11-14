package com.example.hotels.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotels.R
import com.example.hotels.data.repositories.FirestoreRepositoryImpl
import com.example.hotels.domain.models.FavoriteItem
import com.squareup.picasso.Picasso

//class FavoriteAdapter() : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
//    lateinit var repo: FirestoreRepositoryImpl
//       private lateinit var favoriteList: MutableList<FavoriteItem>
//    lateinit var removelistner: removeInterface
//    lateinit var context: Context
//
//        inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//            val name: TextView = itemView.findViewById(R.id.hotelName_favorite)
//            val image: ImageView =itemView.findViewById(R.id.hotel_image_favorite)
//            val price: TextView = itemView.findViewById(R.id.hotelPrice_favorite)
//            val location: TextView=itemView.findViewById(R.id.hotelLocation_favorite)
//            val favButton: CheckBox=itemView.findViewById(R.id.favorite_button_fv)
//            val addToCart: Button=itemView.findViewById(R.id.addToCard_favorite)
//
//            init {
//                favButton.setOnClickListener {
//                    val i: Int = adapterPosition
//                    repo.removeFromCard().remove(i)
//                }
//                addToCart.setOnClickListener {
//                    val i: Int = adapterPosition
//                    repo.addToCardItem().addtoCart(i)
//                }
//            }
//
//        }
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
//            val view = LayoutInflater.from(context).inflate(R.layout.favorite_item, parent, false)
//            return FavoriteViewHolder(view)
//        }
//
//        override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
//            val favoriteItem = favoriteList[position]
//            holder.apply {
//                price.text = favoriteItem.price
//                name.text = favoriteItem.name
//                Picasso.get().load(favoriteItem.image).into(image)
//                location.text=favoriteItem.location
//
//
//            }
//        }
//
//        override fun getItemCount(): Int {
//            return favoriteList.size
//        }
//
//        interface removeInterface {
//            fun remove(position: Int)
//            fun addtoCart(position: Int)
//        }
//    }