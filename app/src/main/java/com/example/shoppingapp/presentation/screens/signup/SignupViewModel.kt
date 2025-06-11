package com.example.shoppingapp.presentation.screens.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.common.Resource
import com.example.shoppingapp.domain.use_cases.auth.SignupUseCse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupUseCase: SignupUseCse
) : ViewModel() {

    // State for the signup screen
    data class SignupState(
        val email: String = "",
        val password: String = "",
        val isLoading: Boolean = false,
        val emailError: String? = null,
        val passwordError: String? = null,
        val signupSuccess: Boolean = false,
        val errorMessage: String? = null
    )

    // Mutable state
    private val _state = mutableStateOf(SignupState())
    val state: State<SignupState> = _state

    // Handle email changes
    fun onEmailChange(email: String) {
        _state.value = _state.value.copy(
            email = email,
            emailError = null
        )
    }

    // Handle password changes
    fun onPasswordChange(password: String) {
        _state.value = _state.value.copy(
            password = password,
            passwordError = null
        )
    }

    // Validate email and password
    private fun validate(): Boolean {
        val email = _state.value.email
        val password = _state.value.password

        var isValid = true

        // Email validation
        if (email.isBlank()) {
            _state.value = _state.value.copy(emailError = "Email is required")
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _state.value = _state.value.copy(emailError = "Please enter a valid email")
            isValid = false
        }

        // Password validation
        if (password.isBlank()) {
            _state.value = _state.value.copy(passwordError = "Password is required")
            isValid = false
        } else if (password.length < 6) {
            _state.value = _state.value.copy(passwordError = "Password must be at least 6 characters")
            isValid = false
        }

        return isValid
    }

    // Perform signup
    fun signUp() {
        if (!validate()) return

        _state.value = _state.value.copy(
            isLoading = true,
            errorMessage = null
        )

        viewModelScope.launch {
            signupUseCase(
                email = _state.value.email,
                password = _state.value.password
            ) { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            signupSuccess = true,
                            errorMessage = null
                        )
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            errorMessage = result.message ?: "An unknown error occurred"
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                }
            }
        }
    }

    // Reset error message
    fun resetErrorMessage() {
        _state.value = _state.value.copy(errorMessage = null)
    }
}