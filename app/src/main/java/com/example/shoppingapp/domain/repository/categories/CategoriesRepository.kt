package com.example.shoppingapp.domain.repository.categories

import com.example.shoppingapp.domain.model.DataItem

interface CategoriesRepository {
    suspend fun getCategories(): DataItem

}