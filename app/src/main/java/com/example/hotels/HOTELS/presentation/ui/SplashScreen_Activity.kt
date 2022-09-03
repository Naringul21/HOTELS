package com.example.hotels.HOTELS.presentation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.hotels.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.splash_screen_activity.*

class SplashScreen_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen_activity)
        val url = "https://cdn.vectorstock.com/images/1000x1000/57/82/20135782.jpg?"
        var Splash_image = splash_image
        Picasso.get().load("https://cdn.vectorstockl.com/images/1000x1000/57/82/20135782.jpg?")
            .error(R.mipmap.splash_image_foreground).into(Splash_image)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, FirstActivity::class.java))
            finish()
        }, 7000)
    }
}