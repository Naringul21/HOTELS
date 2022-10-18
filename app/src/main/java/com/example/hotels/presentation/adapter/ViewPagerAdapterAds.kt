package com.example.hotels.HOTELS.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapterAds(fm: FragmentManager, lifecycle: Lifecycle, var list: List<Fragment>): FragmentStateAdapter(fm, lifecycle) {
    override fun getItemCount(): Int=list.size

    override fun createFragment(position: Int): Fragment=list[position]
}