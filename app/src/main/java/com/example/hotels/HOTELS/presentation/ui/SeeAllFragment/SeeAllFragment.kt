package com.example.hotels.HOTELS.presentation.ui.SeeAllFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotels.HOTELS.data.db.Hotels
import com.example.hotels.HOTELS.presentation.adapter.RvHomeFragmentAdapter
import com.example.hotels.HOTELS.presentation.adapter.RvLatestSearchAdapter
import com.example.hotels.HOTELS.presentation.adapter.RvSeeAllListAdapter
import com.example.hotels.HOTELS.presentation.ui.HomeFragment.HomeViewModel
import com.example.hotels.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_see_all.*


class SeeAllFragment : Fragment(R.layout.fragment_see_all) {

    lateinit var recyclerView: RecyclerView
    private lateinit var hotelArrayList: ArrayList<Hotels>
    private lateinit var seeAllListAdapter: RvSeeAllListAdapter
    lateinit var db: FirebaseFirestore

    private val viewModel by lazy { ViewModelProviders.of(this)[SeeAllFragmentViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        observeData()
    }

    private fun observeData() {
        viewModel.fetchData().observe(viewLifecycleOwner, Observer {
            rv_see_all_hotels.layoutManager = LinearLayoutManager(activity)
            rv_see_all_hotels.setHasFixedSize(true)
            seeAllListAdapter = RvSeeAllListAdapter()
            seeAllListAdapter.setListData(it)
            rv_see_all_hotels.adapter = seeAllListAdapter
            seeAllListAdapter.notifyDataSetChanged()
        })

    }

}