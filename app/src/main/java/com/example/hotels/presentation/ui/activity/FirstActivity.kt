package com.example.hotels.presentation.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.hotels.HOTELS.domain.repositorys.FirebaseInstanceRepository
import com.example.hotels.HOTELS.presentation.ui.login_fragment.LogInFragmentViewModel
import com.example.hotels.HOTELS.presentation.ui.sign_up_fragment.SignUpViewModel
import com.example.hotels.R
import com.example.hotels.databinding.FirstActivityLayoutBinding
import com.example.hotels.utils.ViewManager
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
private const val SIGN_OUT_BUTTON = "Sign Out"
class FirstActivity : AppCompatActivity(), ViewManager {

    private lateinit var binding: FirstActivityLayoutBinding
    private lateinit var popupMenu: PopupMenu
    private lateinit var navController: NavController
    private val viewModel by lazy { FirstActivityViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_activity_layout)

        val navHostFragment=supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController=navHostFragment.navController
        val navGraph=navController.navInflater.inflate(R.navigation.my_nav_graph)



    }

    override fun toolBarAndBottomNavigationBarVisibility(isVisible: Boolean) {
        if (isVisible) {
            binding.apply {
                bottomMenu.visibility = View.VISIBLE
                toolbar.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                bottomMenu.visibility = View.GONE
                toolbar.visibility = View.GONE
            }
        }
    }

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