package com.example.hotels.HOTELS.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hotels.HOTELS.presentation.adapter.ViewPagerAdapter
import com.example.hotels.HOTELS.presentation.ui.HomeFragment.HomeFragment
import com.example.hotels.HOTELS.presentation.ui.LogInFragment.LogInFragment
import com.example.hotels.HOTELS.presentation.ui.SeeAllFragment.SeeAllFragment
import com.example.hotels.HOTELS.presentation.ui.sign_up_fragment.SignUpFragment
import com.example.hotels.R
import kotlinx.android.synthetic.main.fragment_on_boarding.view.*


class OnBoardingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_on_boarding, container, false)

        val fragmentList = listOf<Fragment>(
            SignUpFragment(),
            LogInFragment(),
            HomeFragment(),
            SeeAllFragment(),
        )
        val adapter =
            ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle, fragmentList)

        view.view_pager.adapter = adapter




        return view
    }


}