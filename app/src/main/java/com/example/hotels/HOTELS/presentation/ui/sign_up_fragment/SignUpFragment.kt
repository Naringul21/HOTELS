package com.example.hotels.HOTELS.presentation.ui.sign_up_fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.hotels.HOTELS.domain.repositorys.FirebaseInstanceRepository
import com.example.hotels.HOTELS.presentation.ui.FirstActivity
import com.example.hotels.HOTELS.utils.Resources
import com.example.hotels.R
import kotlinx.android.synthetic.main.fragment_sign_up.*


class SignUpFragment : Fragment(R.layout.fragment_sign_up), View.OnClickListener {
    lateinit var viewModel: SignUpViewModel
    lateinit var repo: FirebaseInstanceRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as FirstActivity).viewModel


        viewModel.result?.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resources.Success -> {
                    hideProgressBar()
                    button_continue.setOnClickListener(this)

                }
                is Resources.Error -> {
                    hideProgressBar()
                    Toast.makeText(requireContext(), "Please wait a few minutes and before you try again", Toast.LENGTH_SHORT).show()

                }
                is Resources.Loading -> {
                    showProgressBar()
                }
            }
        })

}

    private fun hideProgressBar() {
        progress_bar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun onClick(p0: View?) {
        when(p0){
            button_continue->{findNavController().navigate(R.id.login_fragment)}
        }
    }

}