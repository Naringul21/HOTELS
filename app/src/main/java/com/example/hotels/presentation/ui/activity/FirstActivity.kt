package com.example.hotels.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.hotels.R
import com.example.hotels.databinding.FirstActivityLayoutBinding
import kotlinx.android.synthetic.main.first_activity_layout.*

private const val SIGN_OUT_BUTTON = "Sign Out"
class FirstActivity : AppCompatActivity() {

    private lateinit var binding: FirstActivityLayoutBinding
    private lateinit var popupMenu: PopupMenu
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val viewModel by lazy { FirstActivityViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FirstActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
         navController=navHostFragment.navController

        setBottomNavigation()
        hideNavBars()
        initializePopUpMenu()


    }

    private fun hideNavBars() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.onBoardingFragment->{
                    binding.toolbar.visibility = View.GONE
                    binding.bottomMenu.visibility = View.GONE
                }
                R.id.logInFragment -> {
                    binding.toolbar.visibility = View.GONE
                    binding.bottomMenu.visibility = View.GONE
                }

                R.id.signUpFragment -> {
                    binding.toolbar.visibility = View.GONE
                    binding.bottomMenu.visibility = View.GONE
                }

                else -> {
                    binding.toolbar.visibility = View.VISIBLE
                    binding.bottomMenu.visibility = View.VISIBLE
                }
            }
        }

    }

    private fun setBottomNavigation() {
        binding.bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> navController.navigate(R.id.homeFragment)
                R.id.cart -> navController.navigate(R.id.cartFragment2)
            }
            true
        }}

    private fun initializePopUpMenu() {
        popupMenu = PopupMenu(this, binding.menuImage)
        popupMenu.menu.add(SIGN_OUT_BUTTON)
        popupMenu.setOnMenuItemClickListener {
            when (it.toString()) {
                SIGN_OUT_BUTTON -> {
                    viewModel.signOut()
                    navController.navigate(R.id.logInFragment, null,
                   NavOptions.Builder().setPopUpTo(navController.graph.startDestinationId, true).build())
                }
            }
            return@setOnMenuItemClickListener true
        }
    }
}