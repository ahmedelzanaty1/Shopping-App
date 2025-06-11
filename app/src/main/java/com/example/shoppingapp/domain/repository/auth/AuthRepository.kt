package com.example.shoppingapp.domain.repository.auth

import com.example.shoppingapp.common.Resource
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun login(email: String, password: String , onResult : (Resource<Unit>) -> Unit)
    suspend fun signup(email: String, password: String , onResult : (Resource<Unit>) -> Unit)
    suspend fun getCurrentUser(): Resource<FirebaseUser?>


}