package com.example.hotels.HOTELS.presentation.ui.login_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.hotels.HOTELS.data.models.Hotels
import com.example.hotels.HOTELS.data.models.User
import com.example.hotels.HOTELS.presentation.ui.home_fragment.HomeFragment
import com.example.hotels.HOTELS.utils.Navigator
import com.example.hotels.HOTELS.utils.showSnackbar
import com.example.hotels.R
import com.example.hotels.databinding.FragmentLogInBinding
import kotlinx.android.synthetic.main.fragment_log_in.*
import kotlinx.android.synthetic.main.fragment_sign_up.*

class LogInFragment : Fragment(R.layout.fragment_log_in), View.OnClickListener {
    private var _binding: FragmentLogInBinding? = null
    private val binding: FragmentLogInBinding get() = _binding!!

    private val viewModel by lazy { LogInFragmentViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_continue_login.setOnClickListener(this)
        text_button_signUp.setOnClickListener(this)

        initObserve()

    }

    private fun initObserve() {
        with(binding) {
            with(viewModel) {

                isSignIn.observe(viewLifecycleOwner) { it ->
                    if (it) {
                        findNavController().navigate(R.id.homeFragment)
                    } else {
                        emailInputLayoutLogin.error = getString(R.string.invalid_mail)
                        passwordInputLayoutLogin.error = getString(R.string.password_match_error)

                        showSnackbar(requireView(), R.string.wrong_email_password)
                    }
                }
                isInfosValid.observe(viewLifecycleOwner) {
                    if (it == false) {
                        emailInputLayoutLogin.error = getString(R.string.invalid_mail)
                        passwordInputLayoutLogin.error = getString(R.string.password_match_error)
                        showSnackbar(requireView(), R.string.incomplete_information_entered)
                    }
                }
            }
        }
    }

    private fun signInButton(email: String, password: String) {
        viewModel.signIn(email, password)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(p0: View?) {
        when (p0) {
            button_continue_login -> {
                signInButton(edit_emailLogin.text.toString(), edit_passwordLogin.text.toString())


            }
            text_button_signUp -> {
                findNavController().navigate(R.id.signUpFragment)
            }
        }
    }

}