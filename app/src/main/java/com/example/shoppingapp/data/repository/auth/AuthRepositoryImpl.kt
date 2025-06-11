package com.example.shoppingapp.data.repository.auth

import com.example.shoppingapp.common.Resource
import com.example.shoppingapp.data.firebase.FireBaseOnlineDataSource
import com.example.shoppingapp.domain.repository.auth.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FireBaseOnlineDataSource
) : AuthRepository{
    override suspend fun login(
        email: String,
        password: String,
        onResult: (Resource<Unit>) -> Unit
    ) {
        auth.login(email, password, onResult)
    }

    override suspend fun signup(
        email: String,
        password: String,
        onResult: (Resource<Unit>) -> Unit
    ) {
        auth.signUp(email, password, onResult)
    }

    override suspend fun getCurrentUser(): Resource<FirebaseUser?> {
        return auth.getCurrentUser()
    }

}