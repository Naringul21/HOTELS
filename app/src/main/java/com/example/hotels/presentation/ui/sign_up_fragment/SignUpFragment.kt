package com.example.hotels.HOTELS.presentation.ui.sign_up_fragment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hotels.HOTELS.utils.showSnackbar
import com.example.hotels.R
import com.example.hotels.databinding.FragmentSignUpBinding
import kotlinx.android.synthetic.main.fragment_sign_up.*


class SignUpFragment : Fragment(R.layout.fragment_sign_up), View.OnClickListener {
    private var _binding: FragmentSignUpBinding? = null
    private val binding: FragmentSignUpBinding get() = _binding!!

    private val viewModel by lazy { SignUpViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentSignUpBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_register_signup.setOnClickListener(this)
        button_signup_login.setOnClickListener(this)
        initObservers()


    }

    private fun initObservers() {

        with(binding) {

            with(viewModel) {

                isInfosValid.observe(viewLifecycleOwner) {
                    if (it == false)
                        emailInputLayout.error = getString(R.string.invalid_mail)
                    passwordInputLayout.error = getString(R.string.password_match_error)
                    fullNameInputLayout.error = getString(R.string.fullname_empty)
                    showSnackbar(
                        requireView(),
                        R.string.incomplete_information_entered
                    )
                }

                isValidMail.observe(viewLifecycleOwner) {
                    if (it == false) {
                        emailInputLayout.error = getString(R.string.invalid_mail)
                        showSnackbar(requireView(), R.string.invalid_mail)
                    } else {
                        emailInputLayout.error = ""
                    }
                }

                isPasswordMatch.observe(viewLifecycleOwner) {
                    if (it == false) {
                        passwordInputLayout.error = getString(R.string.password_match_error)
                        showSnackbar(requireView(), R.string.password_match_error)
                    } else {
                        passwordInputLayout.error = ""

                    }
                }

                isSignUp.observe(viewLifecycleOwner) {
                    if (it == true) {
                        showSnackbar(
                            requireView(),
                            R.string.sign_up_reg_success_text
                        )
                        clearFields()

                    } else {
                        showSnackbar(requireView(), R.string.registration_is_failed)
                    }
                }
            }
        }
    }

    private fun signUpButton(
        email: String,
        password: String,
        fullname: String,
    ) {
        viewModel.signUp(email, password, fullname)
    }

    private fun clearFields() {
        with(binding) {
            edit_email.setText("")
            emailInputLayout.error = ""
            edit_password.setText("")
            passwordInputLayout.error = ""
            edit_fullName.setText("")
            fullNameInputLayout.error = ""
        }
    }

//    private fun showWarning() {
//      with(binding){
//          with(viewModel){
//          isInfosValid.observe(viewLifecycleOwner) {
//            if (it == false)
//             binding.emailInputLayout.error = getString(R.string.invalid_mail)
//            passwordInputLayout.error = getString(R.string.password_match_error)
//            fullNameInputLayout.error = getString(R.string.fullname_empty)
//            showSnackbar(
//                requireView(),
//                R.string.incomplete_information_entered
//            )
//
////            if (it == false)
//
////        }
//        }}}
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onClick(p0: View?) {
        when (p0) {
            button_register_signup -> {
                signUpButton(edit_email.text.toString(),
                    edit_password.text.toString(),
                    edit_fullName.text.toString())

            }

            button_signup_login -> {
                findNavController().navigate(R.id.action_signUpFragment_to_logInFragment)
            }
        }
    }
}