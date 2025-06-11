package com.example.shoppingapp.data.firebase

import com.example.shoppingapp.common.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class FireBaseOnlineDataSource @Inject constructor(
    private val auth: FirebaseAuth,
) {
    fun signUp(email: String, password: String, onResult: (Resource<Unit>) -> Unit) {
        onResult(Resource.Loading())
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onResult(Resource.Success(Unit))
            }
            .addOnFailureListener { exception ->
                onResult(Resource.Error(exception.localizedMessage ?: "An error occurred"))
            }
    }

    fun login(email: String, password: String, onResult: (Resource<Unit>) -> Unit) {
        onResult(Resource.Loading())
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onResult(Resource.Success(Unit))
            }
            .addOnFailureListener { exception ->
                onResult(Resource.Error(exception.localizedMessage ?: "An error occurred"))
            }
    }
    fun getCurrentUser(): Resource<FirebaseUser?> {
        val user = auth.currentUser
        return if (user != null) {
            Resource.Success(user)
        } else {
            Resource.Error("No user is currently signed in")
        }
    }
}