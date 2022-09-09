package com.example.hotels.HOTELS.presentation.ui.HomeFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotels.HOTELS.data.db.Hotels
import com.example.hotels.HOTELS.presentation.adapter.RvHomeFragmentAdapter
import com.example.hotels.HOTELS.presentation.adapter.RvLatestSearchAdapter
import com.example.hotels.R
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home), View.OnClickListener {
    lateinit var recyclerView: RecyclerView
    private lateinit var hotelArrayList: ArrayList<Hotels>
    private lateinit var homeFragmentAdapter: RvHomeFragmentAdapter
    lateinit var latestSearchAdapter: RvLatestSearchAdapter
    lateinit var db: FirebaseFirestore

    private val viewModel by lazy { ViewModelProviders.of(this)[HomeViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



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
            rv_latest_searching_hotel.layoutManager=LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            rv_latest_searching_hotel.setHasFixedSize(true)
            latestSearchAdapter= RvLatestSearchAdapter()
            latestSearchAdapter.setListData(it)
            rv_latest_searching_hotel.adapter=latestSearchAdapter
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


//        rv_hotel_list_home_seeAll.layoutManager = LinearLayoutManager(activity)
//        rv_hotel_list_home_seeAll.setHasFixedSize(true)
//        hotelArrayList = arrayListOf()
//        HomeFragmentAdapter = rvHomeFragmentAdapter(hotelArrayList)
//        EventChangeListener2()
//        rv_hotel_list_home_seeAll.adapter = HomeFragmentAdapter
//
//
//    }

//    private fun EventChangeListener() {
//        db = FirebaseFirestore.getInstance()
//        db.collection("hotels").orderBy("name", Query.Direction.ASCENDING)
//            .addSnapshotListener(object : EventListener<QuerySnapshot> {
//                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
//                    if (error != null) {
//                        Log.e("FireStrore Error", error.message.toString())
//                        return
//                    }
//                    for (dc: DocumentChange in value?.documentChanges!!) {
//                        if (dc.type == DocumentChange.Type.ADDED) {
//                            hotelArrayList.add(dc.document.toObject(Hotels::class.java))
//                        }
//                    }
//                    latestSearchAdapter.notifyDataSetChanged()
//                }
//            })
//
//    }
//
//    private fun EventChangeListener2() {
//        db = FirebaseFirestore.getInstance()
//        db.collection("hotel_home_fragment").orderBy("name", Query.Direction.ASCENDING)
//            .addSnapshotListener(object : EventListener<QuerySnapshot> {
//                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
//                    if (error != null) {
//                        Log.e("FireStrore Error", error.message.toString())
//                        return
//                    }
//                    for (dc: DocumentChange in value?.documentChanges!!) {
//                        if (dc.type == DocumentChange.Type.ADDED) {
//                            hotelArrayList.add(dc.document.toObject(Hotels::class.java))
//                        }
//                    }
//                    HomeFragmentAdapter.notifyDataSetChanged()
//                }
//            })
//
//    }
