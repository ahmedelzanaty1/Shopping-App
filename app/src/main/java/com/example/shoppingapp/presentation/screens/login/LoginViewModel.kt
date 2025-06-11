package com.example.shoppingapp.presentation.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.common.Resource
import com.example.shoppingapp.domain.use_cases.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    // State for the login screen
    data class LoginState(
        val email: String = "",
        val password: String = "",
        val isLoading: Boolean = false,
        val emailError: String? = null,
        val passwordError: String? = null,
        val loginSuccess: Boolean = false,
        val errorMessage: String? = null
    )

    // Mutable state
    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

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

    private fun validate(): Boolean {
        val email = _state.value.email
        val password = _state.value.password

        var isValid = true

        if (email.isBlank()) {
            _state.value = _state.value.copy(emailError = "Email is required")
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _state.value = _state.value.copy(emailError = "Please enter a valid email")
            isValid = false
        }

        if (password.isBlank()) {
            _state.value = _state.value.copy(passwordError = "Password is required")
            isValid = false
        }

        return isValid
    }

    fun login() {
        if (!validate()) return

        _state.value = _state.value.copy(
            isLoading = true,
            errorMessage = null
        )

        viewModelScope.launch {
            loginUseCase(
                email = _state.value.email,
                password = _state.value.password
            ) { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            loginSuccess = true,
                            errorMessage = null
                        )
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            errorMessage = result.message ?: "Login failed. Please try again."
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                }
            }
        }
    }

    fun resetErrorMessage() {
        _state.value = _state.value.copy(errorMessage = null)
    }
}