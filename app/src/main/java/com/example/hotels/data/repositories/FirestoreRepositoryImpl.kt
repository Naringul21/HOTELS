package com.example.hotels.data.repositories

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hotels.HOTELS.data.models.Hotels
import com.example.hotels.HOTELS.presentation.ui.detail_fragment.DetailFragment
import com.example.hotels.domain.models.CardItems
import com.example.hotels.domain.repositories.FirestoreRepository
import com.example.hotels.presentation.ui.CartFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirestoreRepositoryImpl :FirestoreRepository {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val db = Firebase.firestore

    override fun addToCardItem(fragment: DetailFragment, card: CardItems) {
        db.collection("cart_items")
            .document()
            .set(card, SetOptions.merge())
            .addOnSuccessListener {
                fragment.addToCartDone()

            }
            .addOnFailureListener { e ->
                Log.e("firestore", "Error while adding product to cart", e)
            }
    }


    override fun removeFromCard(fragment: CartFragment, id: String, position: Int) {
        db.collection("hotels")
            .document(id)
            .delete()
            .addOnSuccessListener {
                when (fragment) {
                    is CartFragment -> {
                        fragment.cartItemRemoveDone(position)
                    }
                }
            }
            .addOnFailureListener { e ->
                Log.e("firestore", "Error while deleting  cart items", e)
            }
    }

    override fun getHotelData(): LiveData<MutableList<Hotels>> {
        val mutableData = MutableLiveData<MutableList<Hotels>>()
        coroutineScope.launch { }
        FirebaseFirestore.getInstance().collection("hotelList_seeAll_fragments").get()
            .addOnSuccessListener {
                val listData: MutableList<Hotels> = mutableListOf<Hotels>()
                for (document: QueryDocumentSnapshot in it) {
                    val image: String = document.getString("image") ?: ""
                    val location: String = document.getString("infoLocation") ?: ""
                    val name: String = document.getString("name") ?: ""
                    val price: String = document.getString("price") ?: ""
                    val room: String = document.getString("infoRoom") ?: ""
                    val meal: String = document.getString("infoMeal") ?: ""
                    val description: String = document.getString("description") ?: ""
                    val lat: Double = document.getDouble("lat") ?: 0.0
                    val lng: Double = document.getDouble("lng") ?: 0.0
                    val rating: Long = document.getLong("rating") ?: 0
                    val checkout_quantity: String = document.getString("checkout_quantity") ?: ""
                    val hotels =
                        Hotels(name,
                            location,
                            image,
                            price,
                            meal,
                            room,
                            description,
                            lat,
                            lng,
                            rating,
                            checkout_quantity)
                    listData.add(hotels)
                }
                mutableData.value = listData
            }
        return mutableData
    }

    fun getCartItems(fragment: Fragment): LiveData<MutableList<CardItems>> {
        val cardData = MutableLiveData<MutableList<CardItems>>()
        val cardList = ArrayList<CardItems>()
        db.collection("cart_items")
            .get()
            .addOnSuccessListener {
                val listData: MutableList<CardItems> = mutableListOf<CardItems>()
                for (document: QueryDocumentSnapshot in it) {
                    val image: String = document.getString("image") ?: ""
                    val name: String = document.getString("name") ?: ""
                    val price: String = document.getString("price") ?: ""
                    val cardItem =
                        CardItems(name,
                            image,
                            price)
                    listData.add(cardItem)
                }
                cardData.value = listData

                when (fragment) {
                    is CartFragment ->
                        fragment.getCartItemsDone(cardList)

                }
            }
        return cardData
    }

    fun updateMyCart(
        fragment: Fragment,
        cart_id: String,
        itemHashMap:HashMap<String,Any>,
        position: Int
    ) {
        db.collection("cart_items")
            .document(cart_id)
            .update(itemHashMap)
            .addOnSuccessListener {
                when (fragment) {
                    is CartFragment -> {
                        db.collection("cart_items").document(cart_id).get().addOnSuccessListener {
                            val item: CardItems? = it.toObject(CardItems::class.java)
                            fragment.cartItemUpdateDone(item!!, position)
                        }
                            .addOnFailureListener { e ->
                                Log.e("firestore",
                                    "Error while getting the updated item from cart items",
                                    e)
                            }

                    }
                }
            }
            .addOnFailureListener { e ->
                Log.e("firestore", "Error while updating  cart items", e)

            }
    }

    fun checkIfItemAlreadyInCart(activity: DetailFragment,hotelId: String){
        db.collection("cart_items")
            .whereEqualTo("id",getCurrentUserID())
            .whereEqualTo("product_id",hotelId)
            .get()
            .addOnSuccessListener { document->
                if(document.documents.size>0){
                    activity.productAlreadyInCart()
                }
            }
            .addOnFailureListener {e->
                Log.e("firestore","Error while adding product to cart",e)
            }
    }

    fun getCurrentUserID(): String {
        val currentUser = FirebaseAuth.getInstance().currentUser

        var currentUserId = ""
        if (currentUser != null) {
            currentUserId = currentUser.uid
        }
        return currentUserId
    }


}