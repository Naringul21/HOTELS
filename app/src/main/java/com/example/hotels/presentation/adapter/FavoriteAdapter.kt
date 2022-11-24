package com.example.hotels.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hotels.HOTELS.data.models.Hotels
import com.example.hotels.HOTELS.utils.showSnackbar
import com.example.hotels.R
import com.example.hotels.data.repositories.FirestoreRepositoryImpl
import com.example.hotels.domain.models.CardItems
import com.example.hotels.domain.models.FavoriteItem
import com.example.hotels.presentation.ui.favorite.FavoriteFragment
import com.example.hotels.presentation.ui.favorite.FavoriteViewModel
import com.squareup.picasso.Picasso
import java.util.*

class FavoriteAdapter(private val context: Context) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private var repo= FirestoreRepositoryImpl()
    private var favoriteItem = mutableListOf<FavoriteItem>()
    val viewModel=FavoriteViewModel()
    private val cardItems=CardItems()
    private val hotel=Hotels()

    fun setListData(data: MutableList<FavoriteItem>) {
        favoriteItem = data
    }

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val name: TextView = itemView.findViewById(R.id.hotelName_favorite)
            val image: ImageView =itemView.findViewById(R.id.hotel_image_favorite)
            val price: TextView = itemView.findViewById(R.id.hotelPrice_favorite)
            val location: TextView=itemView.findViewById(R.id.hotelLocation_favorite)
            val favButton: ImageView=itemView.findViewById(R.id.favorite_button_fv)
            val addToCart: Button=itemView.findViewById(R.id.addToCard_favorite)

            }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.favorite_item, parent, false)
            return FavoriteViewHolder(view)
        }

        override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
            val favorite: FavoriteItem = favoriteItem[position]
            holder.apply {
                price.text = favorite.price
                name.text = favorite.name
                location.text=favorite.location
                Glide.with(context).load(favorite.image).into(image)
            }

            holder.favButton.setOnClickListener {
                repo.removeItemFavorite(favorite)
                favoriteItem.removeAt(position)
                this.notifyItemRemoved(position)
                this.notifyItemRangeChanged(0, favoriteItem.size)

            }

            holder.addToCart.setOnClickListener {
                val cartItem = CardItems(
                    userId = FirestoreRepositoryImpl().getCurrentUserID(),
                    name = favorite.name,
                    price = favorite.price,
                    image = favorite.image,
                    id=favorite.id,
                    documentId = UUID.randomUUID().toString()
                )
                repo.addToCardItem(cartItem)
                Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show()
//                showSnackbar( , R.string.add_to_cart)
                repo.removeItemFavorite(favorite)
                favoriteItem.removeAt(position)
                this.notifyItemRemoved(position)
                this.notifyItemRangeChanged(0, favoriteItem.size)
            }

            }




        override fun getItemCount() :Int=favoriteItem.size
    }
