package com.example.hotels.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hotels.HOTELS.utils.showSnackbar
import com.example.hotels.R
import com.example.hotels.data.repositories.FirestoreRepositoryImpl
import com.example.hotels.domain.models.CardItems
import com.example.hotels.presentation.ui.cart.CartFragment
import com.example.hotels.presentation.ui.cart.CartViewModel
import com.squareup.picasso.Picasso

class CartAdapter(private val context: Context) : RecyclerView.Adapter<CartAdapter.CartHolder>(){
private var repo=FirestoreRepositoryImpl()
    private var cardItems = mutableListOf<CardItems>()
    private val viewModel=CartViewModel()
    private var cartItemList = ArrayList<CardItems>()
    private lateinit var cartAdapter: CartAdapter

    fun setListData(data: MutableList<CardItems>) {
        cardItems = data


    }


    inner class CartHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.hotelName_cartList)
        val image: ImageView = itemView.findViewById(R.id.hotel_image_cartList)
        val price: TextView = itemView.findViewById(R.id.hotelPrice_cartList)
        val close: ImageView=itemView.findViewById(R.id.close_btn)
//        val removeItem: ImageButton = itemView.findViewById(R.id.cart_remove_item)
//        val addItem: ImageButton = itemView.findViewById(R.id.cart_add_item)
//        val quantity: TextView = itemView.findViewById(R.id.cart_item_quantity)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cart_list_item, parent, false)
        return CartHolder(view)
    }

    override fun onBindViewHolder(holder: CartHolder, position: Int) {
        val cartItem: CardItems = cardItems[position]
        holder.apply {
            Glide.with(context).load(cartItem.image).into(image)
           name.text = cartItem.name
            price.text = cartItem.price
//            quantity.text=cartItem.checkout_quantity
        }

        holder.close.setOnClickListener {
            viewModel.removeCartItem(cartItem)
            cardItems.removeAt(position)
            this.notifyItemRemoved(position)
            this.notifyItemRangeChanged(0, cardItems.size)


        }




//        holder.addItem.setOnClickListener {
//            val checkoutQuantity=cartItem.checkout_quantity.toInt()+1
//            cartItem.checkout_quantity=checkoutQuantity.toString()
////            val itemHashMap=HashMap<String,Any>()
////                itemHashMap["checkout_quantity"]=(checkoutQuantity+1).toString()
////                repo.updateMyCart(CartFragment(),cartItem.id,cartItem.checkout_quantity.toInt().toString(),position)
//        }
//        holder.removeItem.setOnClickListener {
//
//            if(cartItem.checkout_quantity=="1"){
//                repo.removeItemCart()
//            }else{
//                val checkoutQuantity=cartItem.checkout_quantity.toInt()
//                val itemHashMap=HashMap<String,Any>()
//
//                itemHashMap["checkout_quantity"]=(checkoutQuantity-1).toString()
//                repo.updateMyCart(CartFragment(),cartItem.id,itemHashMap, position)
//            }
//        }

    }

    override fun getItemCount(): Int = cardItems.size
}