package com.example.shoppingapp.domain.use_cases.auth

import com.example.shoppingapp.common.Resource
import com.example.shoppingapp.domain.repository.auth.AuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Resource<FirebaseUser?> {
        return authRepository.getCurrentUser()
    }
}

