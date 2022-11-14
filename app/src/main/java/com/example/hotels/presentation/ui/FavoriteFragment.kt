package com.example.hotels.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hotels.R

class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

//    private fun onItemFavoriteClick() {
//        favoriteAdapter.onFavoriteItemClick = { data ->
//            val fragment =  DetailsFragment()
//            val bundle   =  Bundle()
//            bundle.putString("id"            , data.id)
//            bundle.putString("name"          , data.name)
//            bundle.putString("mainImage"     , data.imgUrl)
//            bundle.putString("secondImage"   , data.secondImage)
//            bundle.putString("thirdImage"    , data.thirdImage)
//            bundle.putDouble("price"         , data.price)
//            fragment.arguments = bundle
//            findNavController().navigate(R.id.detailsFragment , bundle)
//
//        }
  }
