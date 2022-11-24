package com.example.hotels.presentation.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotels.HOTELS.data.models.Hotels
import com.example.hotels.HOTELS.utils.showSnackbar
import com.example.hotels.R
import com.example.hotels.data.repositories.FirestoreRepositoryImpl
import com.example.hotels.databinding.FragmentCardBinding
import com.example.hotels.domain.models.CardItems
import com.example.hotels.domain.models.FavoriteItem
import com.example.hotels.presentation.adapter.CartAdapter
import com.example.hotels.presentation.adapter.FavoriteAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_card.*

class CartFragment : Fragment() {
    private var _binding: FragmentCardBinding? = null
    private val binding: FragmentCardBinding get() = _binding!!
    private lateinit var cartAdapter: CartAdapter
    private var cartItemList = ArrayList<CardItems>()

    private val viewModel by lazy { ViewModelProviders.of(this)[CartViewModel::class.java] }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCardBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
    }

    private fun observeData() {
        viewModel.getDataCart().observe(viewLifecycleOwner, Observer {
            rv_cart_items.layoutManager = LinearLayoutManager(activity)
            rv_cart_items.setHasFixedSize(true)
            cartAdapter = CartAdapter(requireContext())
            cartAdapter.setListData(it as MutableList<CardItems>)
            rv_cart_items.adapter = cartAdapter
            cartAdapter.notifyDataSetChanged()

        })

    }


    fun getCartItemsDone(cartList: ArrayList<CardItems>) {
        cartItemList.clear()
        cartItemList.addAll(cartList)
        updateCart()
    }

    private fun updateCart() {
        if (cartItemList.size > 0) {
            binding.rvCartItems.visibility = View.VISIBLE

        }
    }

    fun cartItemUpdateDone(item: CardItems, position: Int) {
        cartItemList[position].checkout_quantity = item.checkout_quantity
        cartAdapter.notifyItemChanged(position)
        updateCart()
        getCartItems()
    }

    private fun getCartItems() {
        FirestoreRepositoryImpl().getCartItems(this)
    }

    fun cartItemRemoveDone(position: Int) {
        showSnackbar(requireView(), R.string.remove_from_cart_list)
        cartItemList.removeAt(position)
        cartAdapter.notifyItemRemoved(position)
        cartAdapter.notifyItemRangeChanged(0, cartItemList.size)
        updateCart()
    }


}