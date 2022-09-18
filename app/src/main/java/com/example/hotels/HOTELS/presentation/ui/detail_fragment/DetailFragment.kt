package com.example.hotels.HOTELS.presentation.ui.detail_fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hotels.HOTELS.data.models.Hotels
import com.example.hotels.HOTELS.presentation.ui.onboarding.FirstActivity
import com.example.hotels.R
import com.example.hotels.databinding.FragmentHotelDetailsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.google.type.LatLng
import com.squareup.picasso.Picasso

class DetailFragment : Fragment(R.layout.fragment_hotel_details), OnMapReadyCallback {
    private lateinit var googleMap: GoogleMap
    private var _binding: FragmentHotelDetailsBinding? = null
    private val binding: FragmentHotelDetailsBinding get() = _binding!!
    lateinit var hotel: Hotels


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

    }

    private fun setData() {
     binding.apply {
         nameDetail.text=hotel.name
         priceDetails.text=hotel.price
         priceDetails2.text=hotel.price
         Picasso.get().load(hotel.image).into(imageDetail)

     }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(gMap: GoogleMap) {
       googleMap=gMap

        googleMap.uiSettings.isZoomControlsEnabled=true
        val baku=com.google.android.gms.maps.model.LatLng(hotel.lat, hotel.lng)

        val marker=MarkerOptions().position(baku)
        googleMap.addMarker(marker)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(baku))
    }

}