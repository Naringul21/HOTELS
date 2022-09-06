package com.example.hotels.HOTELS.presentation.ui.HomeFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hotels.HOTELS.data.db.HotelsHomeSeeAll
import com.example.hotels.HOTELS.data.db.hotelLatestSearching
import com.example.hotels.HOTELS.presentation.adapter.rvHomeFragmentAdapter
import com.example.hotels.HOTELS.presentation.adapter.rvLatestSearchAdapter
import com.example.hotels.R
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var recyclerView: RecyclerView
    lateinit var hotelArrayList: ArrayList<hotelLatestSearching>
    lateinit var hotelArrayList2: ArrayList<HotelsHomeSeeAll>
    lateinit var HomeFragmentAdapter: rvHomeFragmentAdapter
    lateinit var latestSearchAdapter: rvLatestSearchAdapter
    lateinit var db: FirebaseFirestore

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_latest_searching_hotel.layoutManager =
            LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        rv_latest_searching_hotel.setHasFixedSize(true)
        hotelArrayList = arrayListOf()
        latestSearchAdapter = rvLatestSearchAdapter(hotelArrayList)
        EventChangeListener()
        rv_latest_searching_hotel.adapter = latestSearchAdapter






        rv_hotel_list_home_seeAll.layoutManager = LinearLayoutManager(activity)
        rv_hotel_list_home_seeAll.setHasFixedSize(true)
        hotelArrayList2 = arrayListOf()
        HomeFragmentAdapter = rvHomeFragmentAdapter(hotelArrayList2)
        EventChangeListener2()
        rv_hotel_list_home_seeAll.adapter = HomeFragmentAdapter


    }

    private fun EventChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("hotels").orderBy("name", Query.Direction.ASCENDING)
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null) {
                        Log.e("FireStrore Error", error.message.toString())
                        return
                    }
                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            hotelArrayList.add(dc.document.toObject(hotelLatestSearching::class.java))
                        }
                    }
                    latestSearchAdapter.notifyDataSetChanged()
                }
            })

    }

    private fun EventChangeListener2() {
        db = FirebaseFirestore.getInstance()
        db.collection("hotel_home_fragment").orderBy("name", Query.Direction.ASCENDING)
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null) {
                        Log.e("FireStrore Error", error.message.toString())
                        return
                    }
                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            hotelArrayList2.add(dc.document.toObject(HotelsHomeSeeAll::class.java))
                        }
                    }
                    HomeFragmentAdapter.notifyDataSetChanged()
                }
            })

    }
}