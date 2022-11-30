package com.example.hotels.presentation.ui

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.hotels.HOTELS.data.models.Hotels
import com.example.hotels.HOTELS.data.models.User
import com.example.hotels.HOTELS.utils.Constants.IMAGE_REQUEST_CODE
import com.example.hotels.HOTELS.utils.Constants.READ_STORAGE_PERMISSION_CODE
import com.example.hotels.HOTELS.utils.Constants.USER_PROFILE_IMAGE
import com.example.hotels.HOTELS.utils.showSnackbar
import com.example.hotels.R
import com.example.hotels.data.repositories.FirestoreRepositoryImpl
import com.example.hotels.databinding.FragmentPersonBinding
import com.example.hotels.databinding.FragmentSeeAllBinding
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_hotel_details.*
import kotlinx.android.synthetic.main.fragment_person.*


class PersonFragment : Fragment() {
    private var _binding: FragmentPersonBinding? = null
    private val binding: FragmentPersonBinding get() = _binding!!
    private var profileImageUrl: String = ""
    private var selectedImageUri: Uri? = null
    private var user = User()
    private val db = Firebase.firestore
    private val storageRef = Firebase.storage.reference


    private val viewModel by lazy { ViewModelProviders.of(this)[PersonViewModel::class.java] }

//    private val selectImage =
//        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
//            selectedImageUri = uri
//            //    binding.userPhoto.setImageURI(uri)
//            Glide.with(this)
//                .load(uri)
//                .placeholder(R.drawable.ic_baseline_favorite_border_24)
//                .centerCrop()
//                .apply(RequestOptions.circleCropTransform())
//                .into(binding.person3)
//
//        }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//
////        pickImage()
////        val intent = Intent()
////        onActivityResult(1, 1, intent)
//
//
//        }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View? {
//        _binding = FragmentPersonBinding.inflate(inflater, container, false)
//
//        return binding.root
//
//
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//
//        binding.person3.setOnClickListener()
//        {
//            pickImage()
//            val intent = Intent()
//            onActivityResult(1, 1, intent)
//
//
//        }
//
//
//
//
//            binding.updateProfile.setOnClickListener {
//                val imageUrl:String=""
//                if (selectedImageUri != null) {
//                    FirestoreRepositoryImpl().uploadImageToCloudStorage(requireContext(),
//                        selectedImageUri!!,
//                        USER_PROFILE_IMAGE)
//                    profileImageUrl = imageUrl
//                    updateProfileDetails()
//                } else {
//                    updateProfileDetails()
//                }
//
//            }
//
//
//        }
//    private fun setUser() {
//        var dataList = mutableListOf<User>()
//        viewModel.getUserInfo().observe(viewLifecycleOwner) {
//            binding.apply {
//                user_fullname.text = user.fullName
//            }
//        }
//    }
//
//    private fun pickImage() {
//
//            if (ContextCompat.checkSelfPermission(requireContext(),
//                    android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
//            ) {
//
//                selectImage.launch("image/*")
//
//            } else {
//
//                ActivityCompat.requestPermissions(requireActivity(),
//                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
//                    READ_STORAGE_PERMISSION_CODE)
//
//              }
//    }
//
////        private fun pickImageFromGallery() {
////            val intent = Intent(Intent.ACTION_PICK)
////            intent.type = "image/*"
////            startActivityForResult(intent, IMAGE_REQUEST_CODE)
////
////        }
////
////        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
////            super.onActivityResult(requestCode, resultCode, data)
////            if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
////                binding.person3.setImageURI(Uri.parse(data?.data.toString()))
////
////            }
////        }
//
//    private fun observeData() {
////        viewModel.getUserInfo().observe(viewLifecycleOwner, Observer {
////            user_fullname.text = user.fullName
////        })
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String>,
//        grantResults: IntArray,
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when (requestCode) {
//            //read external storage permission reqCode:0
//            READ_STORAGE_PERMISSION_CODE -> {
//                // If request is cancelled, the result arrays are empty.
//                if ((grantResults.isNotEmpty() &&
//                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                ) {
////
//                    selectImage.launch("image/*")
//
//                } else {
//
//                    Toast.makeText(requireContext(),
//                        "You denied the permission,Now you can't upload your profile photo,You can still provide permission from settings",
//                        Toast.LENGTH_LONG).show()
//                }
//                return
//            }
//
//        }
//    }
//
//    private fun updateProfileDetails() {
//        val userHashMap = HashMap<String, Any>()
//
//        if (profileImageUrl.isNotEmpty()) {
//            userHashMap["image"] = profileImageUrl
//        }
//        FirestoreRepositoryImpl().updateUserProfile(userHashMap)
//        showSnackbar(requireView(), R.string.update_profile)
//    }

}


