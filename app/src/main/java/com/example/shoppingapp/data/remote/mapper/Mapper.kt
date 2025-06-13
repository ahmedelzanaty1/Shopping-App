package com.example.shoppingapp.data.remote.mapper

import com.example.shoppingapp.data.remote.dto.DataItemDto
import com.example.shoppingapp.domain.model.DataItem

fun DataItemDto.toModel() : DataItem {
    return DataItem(
        name = name,
        image = image,
        id = id,
        slug = slug,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}