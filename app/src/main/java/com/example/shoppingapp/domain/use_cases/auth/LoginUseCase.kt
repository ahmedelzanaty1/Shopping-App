package com.example.shoppingapp.domain.use_cases.auth

import com.example.shoppingapp.common.Resource
import com.example.shoppingapp.domain.repository.auth.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repo : AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        onResult: (Resource<Unit>) -> Unit
    ) {
        repo.login(email, password, onResult)
    }
}