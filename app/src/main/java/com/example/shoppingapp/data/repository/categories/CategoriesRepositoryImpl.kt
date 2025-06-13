package com.example.shoppingapp.data.repository.categories

import com.example.shoppingapp.data.remote.ApiManager
import com.example.shoppingapp.data.remote.mapper.toModel
import com.example.shoppingapp.domain.model.DataItem
import com.example.shoppingapp.domain.repository.categories.CategoriesRepository
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val apiManager: ApiManager
) : CategoriesRepository {
    override suspend fun getCategories(): DataItem {
        return apiManager.getCategories().toModel()
    }
}