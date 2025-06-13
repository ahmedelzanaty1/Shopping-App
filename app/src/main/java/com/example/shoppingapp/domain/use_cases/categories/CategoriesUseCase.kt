package com.example.shoppingapp.domain.use_cases.categories

import com.example.shoppingapp.common.Resource
import com.example.shoppingapp.domain.model.DataItem
import com.example.shoppingapp.domain.repository.categories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class CategoriesUseCase @Inject constructor(
    private val repo : CategoriesRepository
) {
    operator fun invoke() : Flow<Resource<DataItem>> = flow {
        try {
            emit(Resource.Loading())
            val categories = repo.getCategories()
            emit(Resource.Success(categories))

        }catch (e : Exception){
            emit(Resource.Error(e.message.toString()))

        }catch (e : IOException){
            emit(Resource.Error(e.message.toString()))
        }
    }
}