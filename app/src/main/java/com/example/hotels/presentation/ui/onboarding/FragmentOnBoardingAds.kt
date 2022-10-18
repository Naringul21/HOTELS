package com.example.hotels.HOTELS.presentation.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hotels.HOTELS.presentation.adapter.ViewPagerAdapter
import com.example.hotels.HOTELS.presentation.ui.AdsFragment
import com.example.hotels.R
import kotlinx.android.synthetic.main.fragment_on_boarding_ads.view.*


class FragmentOnBoardingAds : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_on_boarding_ads, container, false)

        val fragmentList = listOf<Fragment>(
            AdsFragment()
        )

        val adapter =
            ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle, fragmentList)

        view.view_pager1.adapter = adapter




    return view
}


}