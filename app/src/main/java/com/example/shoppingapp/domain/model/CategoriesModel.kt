package com.example.shoppingapp.domain.model

data class CategoriesModel(
	val metadata: Metadata? = null,
	val data: List<CategoryModel> = emptyList(),
	val results: Int? = null
)

data class CategoryModel(
	val image: String? = null,
	val createdAt: String? = null,
	val name: String,
	val id: String? = null,
	val slug: String? = null,
	val updatedAt: String? = null
)

