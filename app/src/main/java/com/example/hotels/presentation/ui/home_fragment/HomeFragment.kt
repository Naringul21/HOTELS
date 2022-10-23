package com.example.hotels.HOTELS.presentation.ui.home_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotels.HOTELS.data.models.Hotels
import com.example.hotels.HOTELS.presentation.adapter.RvHomeFragmentAdapter
import com.example.hotels.HOTELS.presentation.adapter.RvLatestSearchAdapter
import com.example.hotels.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home), View.OnClickListener {
    private lateinit var homeFragmentAdapter: RvHomeFragmentAdapter
    lateinit var latestSearchAdapter: RvLatestSearchAdapter

    private val viewModel by lazy { ViewModelProviders.of(this)[HomeViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment=childFragmentManager.findFragmentById(R.id.containerAds) as NavHostFragment
        val navController=navHostFragment.navController


        observeData()
        text_see_all.setOnClickListener(this)


    }

    private fun observeData() {
        viewModel.fetchData().observe(viewLifecycleOwner, Observer {
            rv_hotel_list_home_seeAll.layoutManager = LinearLayoutManager(activity)
            rv_hotel_list_home_seeAll.setHasFixedSize(true)
            homeFragmentAdapter = RvHomeFragmentAdapter()
            homeFragmentAdapter.setListData(it)
            rv_hotel_list_home_seeAll.adapter = homeFragmentAdapter
            homeFragmentAdapter.notifyDataSetChanged()
        })



        viewModel.fetchData().observe(viewLifecycleOwner, Observer {

                rv_latest_searching_hotel.layoutManager =
                    LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
                rv_latest_searching_hotel.setHasFixedSize(true)
                latestSearchAdapter = RvLatestSearchAdapter()
                latestSearchAdapter.setListData(it)
                rv_latest_searching_hotel.adapter = latestSearchAdapter
                latestSearchAdapter.notifyDataSetChanged()
            
        })

    }

    override fun onClick(p0: View?) {
        when(p0){
            text_see_all->{
                findNavController().navigate(R.id.seeAllFragment)
            }
        }
    }
}
