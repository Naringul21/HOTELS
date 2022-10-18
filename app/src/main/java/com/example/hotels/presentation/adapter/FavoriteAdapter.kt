package com.example.hotels.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.hotels.HOTELS.data.models.Hotels
import com.example.hotels.databinding.FavoriteListItemBinding
import com.example.hotels.data.repositorys.FirestoreInstanceRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.HolderFavorite> {
    lateinit var repo: FirestoreInstanceRepository
    private val context: Context
    private var hotelArrayList: ArrayList<Hotels>
    private lateinit var binding: FavoriteListItemBinding

    constructor(context: Context, hotelArrayList: ArrayList<Hotels>) {
        this.context = context
        this.hotelArrayList = hotelArrayList
    }

    inner class HolderFavorite(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hotel_image_seeAll = binding.hotelImageSeeAll
        val hotelName_seeAll = binding.hotelNameSeeAll
        var favorite_button = binding.favoriteButton
        var hotelLocation_seeAll = binding.hotelLocationSeeAll
        var hotelPrice_seeAll = binding.hotelPriceSeeAll
        var locationImage_seeAll = binding.locationImageSeeAll

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderFavorite {
        binding = FavoriteListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return HolderFavorite(binding.root)
    }

    override fun onBindViewHolder(holder: HolderFavorite, position: Int) {
        val model = hotelArrayList[position]

        loadHotelFavorite(model, holder)
    }

    private fun loadHotelFavorite(model: Hotels, holder: FavoriteAdapter.HolderFavorite) {
        val hotelId = model.id
        val mutableData = MutableLiveData<MutableList<Hotels>>()
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
                            rating)
                    listData.add(hotels)
                }
                mutableData.value = listData
            }
    }

    override fun getItemCount(): Int = hotelArrayList.size
}