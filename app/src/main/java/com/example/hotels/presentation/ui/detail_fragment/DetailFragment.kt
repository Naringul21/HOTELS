package com.example.hotels.HOTELS.presentation.ui.detail_fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.hotels.HOTELS.data.models.Hotels
import com.example.hotels.HOTELS.presentation.ui.see_all_fragment.SeeAllFragmentViewModel
import com.example.hotels.HOTELS.utils.showSnackbar
import com.example.hotels.R
import com.example.hotels.data.repositories.FirestoreRepositoryImpl
import com.example.hotels.databinding.FragmentHotelDetailsBinding
import com.example.hotels.domain.models.CardItems
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_list_item.*
import kotlinx.android.synthetic.main.fragment_hotel_details.*

class DetailFragment : Fragment(R.layout.fragment_hotel_details), OnMapReadyCallback {
    private lateinit var googleMap: GoogleMap
    private var _binding: FragmentHotelDetailsBinding? = null
    private val binding: FragmentHotelDetailsBinding get() = _binding!!
    private var hotel= Hotels()
    private var hotelId:String=""
    private lateinit var firebaseAuth: FirebaseAuth
    private var isInMyFavorite = false

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
        _binding= FragmentHotelDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        addToCard.setOnClickListener {
                    addToCart()

                }
        productAlreadyInCart(hotel)
        checkIsFavorite()
        addToFavorite()


            }

    private fun setData() {
     binding.apply {
         description_hotel.text=hotel.description
         nameDetail.text=hotel.name
         priceDetails.text=hotel.price
         priceDetails2.text=hotel.price
         Picasso.get().load(hotel.image).into(imageDetail)

     }
    }

    private fun addToCart() {
        val cartItem = CardItems(
            name = hotel.name,
            price = hotel.price,
            image = hotel.image,
            checkout_quantity = hotel.checkout_quantity,
        )
        FirestoreRepositoryImpl().addToCardItem(cartItem)
        binding.addToCard.visibility=View.GONE
        Snackbar.make(binding.addToCard,"Added to the cart",Snackbar.LENGTH_LONG).show()

    }


    fun productAlreadyInCart(hotels: Hotels) {
            if (FirestoreRepositoryImpl().getCurrentUserID() != hotels.hotel_id) {
                FirestoreRepositoryImpl().checkIfItemAlreadyInCart(this, hotelId)
                binding.addToCard.visibility=View.GONE
            }
        }
private fun addToFavorite(){
    favorite_button_details.setOnClickListener {
        if (isInMyFavorite) {
            viewModel.removeFromFavoriteList()
            showSnackbar(requireView(), R.string.remove_from_favorite_list)
        } else
            viewModel.addFavoriteList()
        showSnackbar(requireView(), R.string.added_to_favorite_list)
    }
}
    private fun checkIsFavorite() {
        firebaseAuth= FirebaseAuth.getInstance()
    val ref = FirebaseDatabase.getInstance().getReference("users")
    ref.child(firebaseAuth.uid!!).child("favorites").child(hotel.hotel_id)
        .addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                isInMyFavorite = snapshot.exists()
                if (isInMyFavorite) {
                   favorite_button_details.setImageResource(R.drawable.ic_baseline_favorite_red)
                }
                else
                   favorite_button_details.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(gMap: GoogleMap) {
       googleMap=gMap

        googleMap.uiSettings.isZoomControlsEnabled=true
        val latlng=com.google.android.gms.maps.model.LatLng(hotel.lat, hotel.lng)

        val marker=MarkerOptions().position(latlng)
        googleMap.addMarker(marker)

        gMap.setMinZoomPreference(6.0f)
        gMap.setMaxZoomPreference(15.0f)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latlng))
    }

}