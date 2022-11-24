package com.example.hotels.HOTELS.presentation.ui.detail_fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.hotels.HOTELS.data.models.Hotels
import com.example.hotels.HOTELS.utils.showSnackbar
import com.example.hotels.R
import com.example.hotels.data.repositories.FirestoreRepositoryImpl
import com.example.hotels.databinding.FragmentHotelDetailsBinding
import com.example.hotels.domain.models.CardItems
import com.example.hotels.domain.models.FavoriteItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_hotel_details.*
import java.util.*

class DetailFragment : Fragment(R.layout.fragment_hotel_details), OnMapReadyCallback {
    private lateinit var googleMap: GoogleMap
    private var _binding: FragmentHotelDetailsBinding? = null
    private val binding: FragmentHotelDetailsBinding get() = _binding!!
    private var hotel = Hotels()
    private var hotelId: String = ""
    private var isInMyFavorite = false
    private var favoriteItem= FavoriteItem()
    private val fav_id=""

    private val viewModel by lazy { ViewModelProviders.of(this)[DetailViewModel::class.java] }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hotel = arguments?.getSerializable("hotel") as Hotels

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHotelDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        addToCart()
        addToFavorite()
        checkCartItem(hotel, hotelId)



    }

    private fun setData() {
        binding.apply {
            description_hotel.text = hotel.description
            nameDetail.text = hotel.name
            priceDetails.text = hotel.price
            priceDetails2.text = hotel.price
            Picasso.get().load(hotel.image).into(imageDetail)

        }
    }

    private fun addToCart() {
        addToCard.setOnClickListener {
            val cartItem = CardItems(
                userId = FirestoreRepositoryImpl().getCurrentUserID(),
                name = hotel.name,
                price = hotel.price,
                image = hotel.image,
                checkout_quantity = hotel.checkout_quantity,
                id=hotel.hotel_id,
                documentId = UUID.randomUUID().toString()
            )
            viewModel.addToCart(cartItem)
            binding.addToCard.visibility =GONE
            showSnackbar(requireView(), R.string.add_to_cart)
        }
    }


    private fun addToFavorite() {
        favorite_button_details.setOnClickListener {
            if (!favoriteItem.isFavorite) {
                favoriteItem.isFavorite = true
                val favoriteItem = FavoriteItem(
                    name = hotel.name,
                    userId = FirestoreRepositoryImpl().getCurrentUserID(),
                    image = hotel.image,
                    location = hotel.location,
                    price = hotel.price,
                    id = hotel.hotel_id,
                    isFavorite = false,
                    documentId = UUID.randomUUID().toString()

                )
                viewModel.addFavoriteList(favoriteItem)
                favorite_button_details.setImageResource(R.drawable.ic_baseline_favorite_red)
                showSnackbar(requireView(), R.string.added_to_favorite_list)

            } else {
                favoriteItem.isFavorite = false
                viewModel.removeFromFavoriteList(favoriteItem)
                favorite_button_details.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                showSnackbar(requireView(), R.string.remove_from_favorite_list)

            }
        }
    }

    fun productAlreadyInCart(){
        binding.addToCard.visibility=GONE
    }

    private fun checkCartItem(hotel: Hotels, hotelId: String) {
        if (FirestoreRepositoryImpl().getCurrentUserID() != hotel.userId) {
            FirestoreRepositoryImpl().checkIfItemAlreadyInCart(this, hotelId)
        }
    }

    override fun onMapReady(gMap: GoogleMap) {
        googleMap = gMap

        googleMap.uiSettings.isZoomControlsEnabled = true
        val latlng = com.google.android.gms.maps.model.LatLng(hotel.lat, hotel.lng)

        val marker = MarkerOptions().position(latlng)
        googleMap.addMarker(marker)

//        gMap.setMinZoomPreference(6.0f)
//        gMap.setMaxZoomPreference(15.0f)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latlng))
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}