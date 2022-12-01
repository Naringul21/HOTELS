package com.example.hotels.utils

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import com.bumptech.glide.module.AppGlideModule
import com.google.firebase.storage.StorageReference
import java.io.InputStream
import com.firebase.ui.storage.images.FirebaseImageLoader


class GlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
            registry.append(StorageReference::class.java, InputStream::class.java, FirebaseImageLoader.Factory())
        }
    }