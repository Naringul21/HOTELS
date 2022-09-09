package com.example.hotels.HOTELS.domain.repositorys


interface FirebaseInstanceRepositoryInterface {
  fun signUp(eMail: String, password: String, fullname: String)
    fun signIn(eMail: String, password: String)
//    fun getHotelsFromFirestore(): Flow<Hotels>
}