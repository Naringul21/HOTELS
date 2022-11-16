package com.example.hotels.HOTELS.presentation.ui.see_all_fragment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotels.HOTELS.data.models.Hotels
import com.example.hotels.HOTELS.presentation.adapter.RvSeeAllListAdapter
import com.example.hotels.HOTELS.utils.Navigator
import com.example.hotels.R
import com.example.hotels.data.repositories.FirestoreRepositoryImpl
import com.example.hotels.databinding.FragmentSeeAllBinding
import com.example.hotels.domain.models.FavoriteItem
import kotlinx.android.synthetic.main.fragment_see_all.*


class AllItemsFragment : Fragment(R.layout.fragment_see_all), Navigator {
    private var _binding: FragmentSeeAllBinding? = null
    private val binding: FragmentSeeAllBinding get() = _binding!!
    private lateinit var seeAllListAdapter: RvSeeAllListAdapter
    private lateinit var repo: FirestoreRepositoryImpl
    lateinit var favoriteItem: FavoriteItem
    lateinit var hotels: Hotels

    private val viewModel by lazy { ViewModelProviders.of(this)[AllItemsFragmentViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentSeeAllBinding.inflate(inflater, container, false)

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        observeData()
        filterList()
//        setAddToFavoritesButtonListener()


    }

    private fun observeData() {
        viewModel.getDataSeeAll().observe(viewLifecycleOwner, Observer {
            rv_see_all_hotels.layoutManager = LinearLayoutManager(activity)
            rv_see_all_hotels.setHasFixedSize(true)
            seeAllListAdapter = RvSeeAllListAdapter(this)
            seeAllListAdapter.setListData(it)
            rv_see_all_hotels.adapter = seeAllListAdapter
            seeAllListAdapter.notifyDataSetChanged()

        })
    }

    override fun navigate(hotels: Hotels) {
        val bundle = Bundle()
        bundle.putSerializable("hotel", hotels)
        findNavController().navigate(R.id.fragment_hotel_details, bundle)
    }

    private fun filterList() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(filterString: String?): Boolean {
                seeAllListAdapter!!.filter.filter(filterString)
                return true
            }

            override fun onQueryTextChange(filterString: String?): Boolean {
                seeAllListAdapter!!.filter.filter(filterString)
                return true
            }

        })
    }

//    private fun setAddToFavoritesButtonListener() {
//        binding.apply {
//            favorite_button_seeAll?.setOnCheckedChangeListener { compoundButton, b ->  }
//                if (!favorite_button_seeAll.isChecked) {
//                    favoriteItem.isFavorite = true
//                    viewModel.addFavoriteList(favoriteItem)
//                    showSnackbar(requireView(), R.string.added_to_favorite_list)
//                } else {
//                    favoriteItem.isFavorite = false
//                    viewModel.removeFromFavoriteList(favoriteItem)
//                    showSnackbar(requireView(), R.string.remove_from_favorite_list)
//                }
//            }
//        }
    }




