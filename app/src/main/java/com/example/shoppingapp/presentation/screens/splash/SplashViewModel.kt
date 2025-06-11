package com.example.shoppingapp.presentation.screens.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.common.Resource
import com.example.shoppingapp.domain.use_cases.auth.GetCurrentUserUseCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {
    sealed class SplashState {
        object Loading : SplashState()
        object NotAuthenticated : SplashState()
        data class Authenticated(val user: FirebaseUser) : SplashState()
    }

    private val _state = mutableStateOf<SplashState>(SplashState.Loading)
    val state: State<SplashState> = _state

    init {
        checkUserAuthentication()
    }

    private fun checkUserAuthentication() {
        viewModelScope.launch {
            delay(2000)

            when (val result = getCurrentUserUseCase()) {
                is Resource.Success -> {
                    result.data?.let { user ->
                        _state.value = SplashState.Authenticated(user)
                    } ?: run {
                        _state.value = SplashState.NotAuthenticated
                    }
                }
                is Resource.Error -> {
                    _state.value = SplashState.NotAuthenticated
                }
                is Resource.Loading -> {
                    _state.value = SplashState.Loading

                }
            }
        }
    }
}