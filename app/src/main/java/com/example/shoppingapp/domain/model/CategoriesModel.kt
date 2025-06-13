package com.example.shoppingapp.domain.model

data class CategoriesModel(
	val metadata: Metadata? = null,
	val data: List<DataItem?>? = null,
	val results: Int? = null
)

data class DataItem(
	val image: String? = null,
	val createdAt: String? = null,
	val name: String ,
	val id: String? = null,
	val slug: String? = null,
	val updatedAt: String? = null
)

data class Metadata(
	val numberOfPages: Int? = null,
	val limit: Int? = null,
	val currentPage: Int? = null
)

