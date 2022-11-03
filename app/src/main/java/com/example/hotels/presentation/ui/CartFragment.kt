package com.example.hotels.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotels.HOTELS.data.models.Hotels
import com.example.hotels.HOTELS.presentation.adapter.RvSeeAllListAdapter
import com.example.hotels.HOTELS.presentation.ui.see_all_fragment.SeeAllFragmentViewModel
import com.example.hotels.databinding.FragmentCardBinding
import com.example.hotels.domain.models.CardItems
import com.example.hotels.presentation.adapter.CartAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.cart_list_item.*
import kotlinx.android.synthetic.main.fragment_card.*
import kotlinx.android.synthetic.main.fragment_see_all.*

class CartFragment : Fragment() {
    private var _binding: FragmentCardBinding? = null
    private val binding: FragmentCardBinding get() = _binding!!
    private lateinit var cartAdapter: CartAdapter
    private lateinit var hotelList: ArrayList<Hotels>
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
            cartAdapter = CartAdapter(requireContext(), cartItemList)
            cartAdapter.setListData(it as MutableList<CardItems>)
            rv_cart_items.adapter = cartAdapter
            cartAdapter.notifyDataSetChanged()

        })
    }

    fun cartItemRemoveDone(position: Int) {
        Snackbar.make(requireView(), "Item is removed from your cart!", Snackbar.LENGTH_LONG).show()
        cartItemList.removeAt(position)
        cartAdapter.notifyItemRemoved(position)
        cartAdapter.notifyItemRangeChanged(0, cartItemList.size)

    }

//    private fun setupRecyclerView(){
//        binding.apply {
//            rv_cart_items.layoutManager = LinearLayoutManager(activity)
//            rv_cart_items.setHasFixedSize(true)
//            cartAdapter = CartAdapter()
//            rv_cart_items.adapter = cartAdapter
//        }
//    }

    fun getCartItemsDone(cartList: ArrayList<CardItems>) {

        cartItemList.clear()
        cartItemList.addAll(cartList)
    }

    private fun updateCart() {
        if (cartItemList.size > 0) {
            binding.rvCartItems.visibility = View.VISIBLE
//            binding.noItemsFound.visibility= View.GONE

        }
    }

    fun cartItemUpdateDone(item:CardItems,position:Int){
        cartItemList[position].checkout_quantity=item.checkout_quantity
        cartAdapter.notifyItemChanged(position)
        updateCart()
//          getCartItems()
    }
}