package com.example.hotels.domain.repositories

interface AuthRepository {
    fun signIn(email: String ,password: String)

    fun signUp(eMail: String, password: String, fullname: String)

    fun signOut()
}