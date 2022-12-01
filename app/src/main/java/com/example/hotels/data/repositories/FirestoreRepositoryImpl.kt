package com.example.hotels.data.repositories

import android.content.Context
import android.net.Uri
import android.util.Log
import android.webkit.MimeTypeMap
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hotels.HOTELS.data.models.Hotels
import com.example.hotels.HOTELS.data.models.User
import com.example.hotels.HOTELS.presentation.ui.detail_fragment.DetailFragment
import com.example.hotels.domain.models.CardItems
import com.example.hotels.domain.models.FavoriteItem
import com.example.hotels.domain.repositories.FirestoreRepository
import com.example.hotels.presentation.ui.cart.CartFragment
import com.example.hotels.presentation.ui.favorite.FavoriteFragment
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import com.google.firebase.auth.FirebaseAuth as FirebaseAuth

class FirestoreRepositoryImpl : FirestoreRepository {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val db = Firebase.firestore
    private lateinit var hotels: ArrayList<Hotels>
    private val firestoreInstance=FirebaseFirestore.getInstance()
    private val currentUserDocRef:DocumentReference
    get()=firestoreInstance.document("users/${getCurrentUserID() ?: throw NullPointerException("UID is null")}")


    override fun addToCardItem(card: CardItems) {
        coroutineScope.launch {
            db.collection("cart_items")
                .document()
                .set(card, SetOptions.merge())
                .addOnSuccessListener {
                    Log.v("cart", "Added to the cart")

                }
                .addOnFailureListener { e ->
                    Log.e("cart", "Error while adding product to cart", e)
                }
        }
    }

    override fun removeItemCart(cart: CardItems) {
        coroutineScope.launch {
            val cartItem = db.collection("cart_items")
            val query = cartItem.whereEqualTo("documentId", cart.documentId)
            query.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        document.reference.delete()
                            .addOnSuccessListener {
                                Log.v("cartttt", "delete cart")

                            }
                            .addOnFailureListener {

                            }
                    }

                }
            }
        }
    }


    override fun addToFavoriteItem(favoriteItem: FavoriteItem) {
        coroutineScope.launch {
            db.collection("favorite_items")
                .document()
                .set(favoriteItem, SetOptions.merge())
                .addOnSuccessListener {
                    Log.d("favorite", "Add item to favorite list")
                }
                .addOnCanceledListener {
                    Log.d("favorite", "Error adding item to favorite list")

                }
        }
    }

    override fun removeItemFavorite(favorite: FavoriteItem) {
        coroutineScope.launch {
            val favoriteItem = db.collection("favorite_items")
            val query = favoriteItem.whereEqualTo("documentId", favorite.documentId)
            query.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        document.reference.delete()
                            .addOnSuccessListener {
                                Log.v("favoritee", "Remove item from favorite list")

                            }
                            .addOnFailureListener {

                            }
                    }
                }
            }
        }
    }


    override fun getHotelData(): LiveData<MutableList<Hotels>> {
        val mutableData = MutableLiveData<MutableList<Hotels>>()
        coroutineScope.launch {
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
                        val id: String = document.getString("hotelId") ?: ""
                        val checkout_quantity: String =
                            document.getString("checkout_quantity") ?: ""
                        val hotels =
                            Hotels(name = name,
                                location = location,
                                image = image,
                                price = price,
                                meal = meal,
                                room = room,
                                description = description,
                                lat = lat,
                                lng = lng,
                                rating = rating,
                                checkout_quantity = checkout_quantity,
                                hotel_id = id)
                        listData.add(hotels)
                    }
                    mutableData.value = listData
                }
        }
        return mutableData
    }

    override fun getCartItems(fragment: Fragment): LiveData<MutableList<CardItems>> {
        val cardData = MutableLiveData<MutableList<CardItems>>()
        val cardList = ArrayList<CardItems>()
        coroutineScope.launch {
            db.collection("cart_items")
                .whereEqualTo("userId", getCurrentUserID())
                .get()
                .addOnSuccessListener {
                    val listData: MutableList<CardItems> = mutableListOf<CardItems>()
                    for (document: QueryDocumentSnapshot in it) {
                        val docId = document.getString("documentId") ?: ""
                        val image: String = document.getString("image") ?: ""
                        val name: String = document.getString("name") ?: ""
                        val price: String = document.getString("price") ?: ""
                        val id: String = document.getString("id") ?: ""
                        val userId: String = document.getString("userId") ?: ""
                        val cardItem =
                            CardItems(
                                name = name,
                                image = image,
                                price = price,
                                id = id,
                                userId = userId,
                                documentId = docId)
                        listData.add(cardItem)
                    }
                    cardData.value = listData

                    when (fragment) {
                        is CartFragment ->
                            fragment.getCartItemsDone(cardList)
                    }
                }
        }
        return cardData
    }


    fun getCurrentUserID(): String {
        val currentUser = FirebaseAuth.getInstance().currentUser

        var currentUserId = ""
        if (currentUser != null) {
            currentUserId = currentUser.uid
        }
        return currentUserId
    }


    fun getFavoriteItems(fragment: Fragment): LiveData<MutableList<FavoriteItem>> {
        val mutableData = MutableLiveData<MutableList<FavoriteItem>>()
        val favoriteList = ArrayList<FavoriteItem>()
        coroutineScope.launch {
            db.collection("favorite_items")
                .whereEqualTo("userId", getCurrentUserID())
                .get()
                .addOnSuccessListener {
                    val listData: MutableList<FavoriteItem> = mutableListOf<FavoriteItem>()
                    for (document: QueryDocumentSnapshot in it) {
                        val docId = document.getString("documentId") ?: ""
                        val image: String = document.getString("image") ?: ""
                        val name: String = document.getString("name") ?: ""
                        val price: String = document.getString("price") ?: ""
                        val location: String = document.getString("location") ?: ""
                        val id: String = document.getString("id") ?: ""
                        val userId: String = document.getString("userId") ?: ""
                        val favorite =
                            FavoriteItem(name = name,
                                image = image,
                                price = price,
                                location = location,
                                id = id,
                                userId = userId,
                                documentId = docId
                            )
                        listData.add(favorite)
                    }
                    mutableData.value = listData
                    when (fragment) {
                        is FavoriteFragment ->
                            fragment.getFavoriteItemsDone(favoriteList)
                    }
                }
        }
        return mutableData
    }

//    fun updateMyCart(
//        fragment: Fragment,
//        cart_id: String,
//        itemHashMap: HashMap<String, Any>,
//        position: Int
//    ) {
//        db.collection("cart_items")
//            .document(cart_id)
//            .update(itemHashMap)
//            .addOnSuccessListener {
//                when (fragment) {
//                    is CartFragment -> {
//                        db.collection("cart_items").document(cart_id).get().addOnSuccessListener {
//                            val item: CardItems? = it.toObject(CardItems::class.java)
//                            fragment.cartItemUpdateDone(item!!, position)
//                        }
//                            .addOnFailureListener { e ->
//                                Log.e("firestore",
//                                    "Error while getting the updated item from cart items",
//                                    e)
//                            }
//
//                    }
//                }
//            }
//            .addOnFailureListener { e ->
//                Log.e("firestore", "Error while updating  cart items", e)
//
//            }
//    }

    fun checkIfItemAlreadyInCart(fragment: DetailFragment, hotelId: String) {
        db.collection("cart_items")
            .whereEqualTo("userId", getCurrentUserID())
            .whereEqualTo("id", hotelId)
            .get()
            .addOnSuccessListener { document ->
                if (document.documents.size > 0) {
                    fragment.productAlreadyInCart()
                }
            }
            .addOnFailureListener { e ->
                Log.e("firestore", "Error while adding product to cart", e)
            }
    }

    fun updateUserProfile(userHashMap: HashMap<String, Any>) {

        db.collection("users")
            .document(getCurrentUserID())
            .update(userHashMap)
            .addOnSuccessListener {
                Log.v("firestore", "Updating user profile")

                }
            .addOnFailureListener { e ->
                Log.e("firestore", "Error updating user profile", e)
            }

    }
    //

    fun uploadImageToCloudStorage(requireContext: Context, imageUri: Uri, imageType:String) {
        val sRef: StorageReference = FirebaseStorage.getInstance().reference.child(
            imageType+System.currentTimeMillis()+"."
                    + MimeTypeMap.getSingleton().getExtensionFromMimeType(requireContext.contentResolver.getType(imageUri))
        )
        sRef.putFile(imageUri)
            .addOnSuccessListener { taskSnapshot ->
                taskSnapshot.metadata!!.reference!!.downloadUrl
                    .addOnSuccessListener { uri ->
                        Log.e("firebase image url: ",
                            taskSnapshot.metadata!!.reference!!.downloadUrl.toString()
                        )
                    }
            }
            .addOnFailureListener { e ->
                Log.e("firebase image url: ", "Don't upload image")

            }
    }

    fun updateCurrentUser(fullname: String="", email: String="", image: String?=null ){
        val userFieldMap= mutableMapOf<String, Any>()
        if (fullname.isNotBlank()) userFieldMap["fullname"]=fullname
        if (email.isNotBlank()) userFieldMap["email"]=email
        if (image!=null) userFieldMap["image"]=image
        currentUserDocRef.update(userFieldMap)
    }

    fun getCurrentUser(onComplete: (User)->Unit){
        currentUserDocRef.get()
            .addOnSuccessListener {
                onComplete(it.toObject(User::class.java)!!)
                Log.v("user", "getUser")
            }
    }

    object Storage {
//        private val storageInstance : FirebaseStorage by lazy { FirebaseStorage.getInstance() }
        private val storageInstancee=FirebaseStorage.getInstance()
        private val currentUserRef: StorageReference
            get()= storageInstancee.reference.child(FirestoreRepositoryImpl().getCurrentUserID() ?: throw NullPointerException("UID is null"))

        fun uploadProfilePhoto(imageBytes: ByteArray, onSuccess: (imagePath: String)-> Unit){
            val ref= currentUserRef.child("image/${UUID.nameUUIDFromBytes(imageBytes)}")
            ref.putBytes(imageBytes)
                .addOnSuccessListener {
                    onSuccess(ref.path)
                    Log.v("storage", "load img")
                }
        }
        fun pathToReference(path: String)= storageInstancee.getReference(path)


    }




//    fun initCurrentUserIfFirstTime(onComplete: () -> Unit) {
//        currentUserDocRef.get().addOnSuccessListener { documentSnapshot ->
//            if (!documentSnapshot.exists()) {
//                val newUser = User(getCurrentUserID(),"",
//                    "", "")
//                currentUserDocRef.set(newUser).addOnSuccessListener {
//                    onComplete()
//                }
//            }
//            else
//                onComplete()
//        }
//    }




}



