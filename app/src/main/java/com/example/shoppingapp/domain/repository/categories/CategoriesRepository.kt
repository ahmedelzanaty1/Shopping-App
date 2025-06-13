package com.example.shoppingapp.domain.repository.categories

import com.example.shoppingapp.domain.model.CategoryModel

interface CategoriesRepository {
    suspend fun getCategories(): List<CategoryModel>

}