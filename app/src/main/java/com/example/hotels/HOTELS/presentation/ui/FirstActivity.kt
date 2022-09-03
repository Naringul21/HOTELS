package com.example.hotels.HOTELS.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.hotels.HOTELS.presentation.ui.sign_up_fragment.SignUpViewModel
import com.example.hotels.R
import com.google.firebase.auth.FirebaseAuth

class FirstActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_activity_layout)

        val navHostFragment=supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController=navHostFragment.navController
        val navGraph=navController.navInflater.inflate(R.navigation.my_nav_graph)


    }
}