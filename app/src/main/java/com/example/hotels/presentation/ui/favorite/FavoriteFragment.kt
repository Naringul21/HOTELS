package com.example.hotels.presentation.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotels.HOTELS.data.models.Hotels
import com.example.hotels.HOTELS.utils.showSnackbar
import com.example.hotels.R
import com.example.hotels.databinding.FragmentCardBinding
import com.example.hotels.databinding.FragmentFavoriteBinding
import com.example.hotels.domain.models.CardItems
import com.example.hotels.domain.models.FavoriteItem
import com.example.hotels.presentation.adapter.CartAdapter
import com.example.hotels.presentation.adapter.FavoriteAdapter
import com.example.hotels.presentation.ui.cart.CartViewModel
import kotlinx.android.synthetic.main.fragment_card.*
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding: FragmentFavoriteBinding get() = _binding!!
    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var hotelList: ArrayList<Hotels>
    private var favoriteItem = ArrayList<FavoriteItem>()

    private val viewModel by lazy { ViewModelProviders.of(this)[FavoriteViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
    }

    private fun observeData() {
        viewModel.getFavoriteItems(this).observe(viewLifecycleOwner, Observer {
            favorite_item1.layoutManager = LinearLayoutManager(activity)
            favorite_item1.setHasFixedSize(true)
            favoriteAdapter = FavoriteAdapter(requireContext())
            favoriteAdapter.setListData(it as MutableList<FavoriteItem>)
            favorite_item1.adapter = favoriteAdapter
            favoriteAdapter.notifyDataSetChanged()
        })

    }

    fun getFavoriteItemsDone(cartList:ArrayList<FavoriteItem>){
        favoriteItem.addAll(cartList)


    }

  }
