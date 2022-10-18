package com.example.hotels.presentation.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.hotels.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.splash_screen_activity.*

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen_activity)
        val splashImage = splash_image
        Picasso.get().load("https://media.istockphoto.com/vectors/tiny-tourists-booking-hotel-online-vector-id1280488705?k=20&m=1280488705&s=612x612&w=0&h=DIQNPxW3CL1CDRaH_U7AfHqAD6HgHir-P2PU_Yme8y4=")
            .error(R.mipmap.splash_image_foreground).into(splashImage)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, FirstActivity::class.java))
            finish()
        }, 3000)
    }
}