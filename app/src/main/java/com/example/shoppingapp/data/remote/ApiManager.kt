package com.example.shoppingapp.data.remote

import com.example.shoppingapp.data.remote.dto.CategoriesResponse
import retrofit2.http.GET

interface ApiManager {
    @GET("/api/v1/categories")
    suspend fun getCategories(): CategoriesResponse
}