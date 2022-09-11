package com.example.hotels.HOTELS.presentation.ui.SeeAllFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotels.HOTELS.data.db.Hotels
import com.example.hotels.HOTELS.presentation.adapter.RvSeeAllListAdapter
import com.example.hotels.HOTELS.utils.Navigator
import com.example.hotels.R
import kotlinx.android.synthetic.main.fragment_see_all.*


class SeeAllFragment : Fragment(R.layout.fragment_see_all), Navigator {

    private lateinit var hotelArrayList: ArrayList<Hotels>
    private lateinit var seeAllListAdapter: RvSeeAllListAdapter

    private val viewModel by lazy { ViewModelProviders.of(this)[SeeAllFragmentViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        observeData()
    }

    private fun observeData() {
        viewModel.fetchData().observe(viewLifecycleOwner, Observer {
            rv_see_all_hotels.layoutManager = LinearLayoutManager(activity)
            rv_see_all_hotels.setHasFixedSize(true)
            seeAllListAdapter = RvSeeAllListAdapter(this)
            seeAllListAdapter.setListData(it)
            rv_see_all_hotels.adapter = seeAllListAdapter
            seeAllListAdapter.notifyDataSetChanged()
        })

    }

    override fun navigate(hotels: Hotels) {
        val bundle=Bundle()
        bundle.putSerializable("hotel", hotels)
        findNavController().navigate(R.id.detailFragment,bundle)
    }

}