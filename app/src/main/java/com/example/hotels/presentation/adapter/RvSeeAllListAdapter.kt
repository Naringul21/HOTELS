package com.example.hotels.HOTELS.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.hotels.HOTELS.data.models.Hotels
import com.example.hotels.HOTELS.presentation.ui.see_all_fragment.SeeAllFragmentViewModel
import com.example.hotels.HOTELS.utils.Navigator
import com.example.hotels.R
import com.example.hotels.data.repositories.FirestoreRepositoryImpl
import com.example.hotels.domain.models.FavoriteItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class RvSeeAllListAdapter(val navigator: Navigator) :
    RecyclerView.Adapter<RvSeeAllListAdapter.MyViewHolder>(), Filterable {
    private var imageClick = false
    lateinit var repo: FirestoreRepositoryImpl
    lateinit var favoriteItem: FavoriteItem
    lateinit var viewModel: SeeAllFragmentViewModel



    fun setListData(data: MutableList<Hotels>) {
        dataList = data
        dataListFilter = data
    }

    inner class MyViewHolder(viewItem: View) : RecyclerView.ViewHolder(viewItem) {
        val name: TextView = itemView.findViewById(R.id.hotelName_seeAll)
        val location: TextView = itemView.findViewById(R.id.hotelLocation_seeAll)
        val image: ImageView = itemView.findViewById(R.id.hotel_image_seeAll)
        val price: TextView = itemView.findViewById(R.id.hotelPrice_seeAll)
        val ratingBar: RatingBar = itemView.findViewById(R.id.rBar_seeAll)

        fun setOnClick(hotels: Hotels) {
            itemView.setOnClickListener {
                navigator.navigate(hotels)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_see_all_list_items, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val hotel: Hotels = dataList[position]
        holder.apply {
            ratingBar.rating = hotel.rating.toFloat()
            name.text = hotel.name
            location.text = hotel.location
            price.text = hotel.price
            Picasso.get().load(hotel.image).into(image)
        }
        holder.setOnClick(hotel)
    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    private var dataList = mutableListOf<Hotels>()
    private var dataListFilter = mutableListOf<Hotels>()


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (charSequence == null || charSequence.length < 0) {
                    filterResults.count = dataListFilter.size
                    filterResults.values = dataListFilter
                } else {
                    val searchChr = charSequence.toString()
                    val itemModal = mutableListOf<Hotels>()

                    for (item in dataListFilter) {
                        if (item.name.contains(searchChr) || item.location.contains(searchChr)) {
                            itemModal.add(item)
                        }
                    }
                    filterResults.count = itemModal.size
                    filterResults.values = itemModal
                }
                return filterResults

            }

            override fun publishResults(p0: CharSequence?, filterResults: FilterResults?) {
                dataList = filterResults!!.values as MutableList<Hotels>
                notifyDataSetChanged()
            }
        }
    }
}

