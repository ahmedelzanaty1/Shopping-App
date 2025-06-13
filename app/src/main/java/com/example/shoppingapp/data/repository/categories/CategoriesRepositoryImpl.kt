package com.example.shoppingapp.data.repository.categories

import com.example.shoppingapp.data.remote.ApiManager
import com.example.shoppingapp.data.remote.mapper.toCategoryModel
import com.example.shoppingapp.domain.model.CategoriesModel
import com.example.shoppingapp.domain.model.CategoryModel
import com.example.shoppingapp.domain.repository.categories.CategoriesRepository
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val apiManager: ApiManager
) : CategoriesRepository {
    override suspend fun getCategories(): List<CategoryModel> {
        return apiManager.getCategories().data
            ?.filterNotNull()
            ?.map { it.toCategoryModel() } ?: emptyList()



    }


}