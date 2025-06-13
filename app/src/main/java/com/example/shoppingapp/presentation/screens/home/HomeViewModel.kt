package com.example.shoppingapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.common.Resource
import com.example.shoppingapp.domain.model.DataItem
import com.example.shoppingapp.domain.use_cases.categories.CategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoriesUseCase: CategoriesUseCase

) : ViewModel() {
    data class CategoriesState(
        val categories: DataItem = DataItem(
            name = "No Data"
        ) ,
        val error: String? = null,
        val isLoading: Boolean = false
    )
    private val _state = MutableStateFlow(CategoriesState())
    val state: StateFlow<CategoriesState> = _state.asStateFlow()
    init {
        getCategories()
    }
    private fun getCategories() {
        viewModelScope.launch {
            categoriesUseCase().collect {
                when (it) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            categories = it.data ?: DataItem(
                                name = "No Data"
                            ),
                            isLoading = false,
                            error = null
                        )

                    }
                    is Resource.Error -> {
                        _state.value = CategoriesState(error = it.message ?: "An unexpected error occurred")
                    }
                    is Resource.Loading -> {
                        _state.value = CategoriesState(isLoading = true)
                    }

                }
            }
        }
    }

}