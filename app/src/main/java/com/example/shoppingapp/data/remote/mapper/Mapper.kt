package com.example.shoppingapp.data.remote.mapper

import com.example.shoppingapp.data.remote.dto.DataItem
import com.example.shoppingapp.domain.model.CategoriesModel
import com.example.shoppingapp.domain.model.CategoryModel

fun DataItem.toCategoryModel(): CategoryModel {
    return CategoryModel(
        image = image,
        createdAt = createdAt,
        name = name ?: "",
        id = id,
        slug = slug,
        updatedAt = updatedAt
    )
}