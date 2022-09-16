package com.example.hotels.HOTELS.presentation.ui.detail_fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hotels.HOTELS.data.models.Hotels
import com.example.hotels.R
import com.example.hotels.databinding.FragmentHotelDetailsBinding

class DetailFragment : Fragment(R.layout.fragment_hotel_details) {
    private var _binding: FragmentHotelDetailsBinding? = null
    private val binding: FragmentHotelDetailsBinding get() = _binding!!
    lateinit var hotel: Hotels


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        hotel = arguments?.getSerializable("hotel") as Hotels
        Log.d("MyTag", hotel.toString())

//        val mapFragment = (R.id.map) as SupportMapFragment
//        mapFragment.getMapAsync(this@DetailFragment)
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
        binding.nameDetail.text = hotel.name
//        setData()
    }

//    private fun setData() {
//
////            priceDetails.text = hotel.price
////            priceDetails2.text=hotel.price
////            description.text = hotel.description
////
////        }
//
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val DETAIL_ARTICLE = "detail_article"
    }

}