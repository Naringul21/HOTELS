package com.example.hotels.HOTELS.presentation.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("app:fromURL")
fun getImagefromUrl (view: ImageView, url: String){
        Picasso.get().load("").into(view)

    }
