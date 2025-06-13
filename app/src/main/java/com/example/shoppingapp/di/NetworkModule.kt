package com.example.shoppingapp.di

import com.example.shoppingapp.common.Utils
import com.example.shoppingapp.data.remote.ApiManager
import com.example.shoppingapp.data.repository.categories.CategoriesRepositoryImpl
import com.example.shoppingapp.domain.repository.categories.CategoriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun ProvideRetrofit () : ApiManager {
        return Retrofit.Builder()
            .baseUrl(Utils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiManager::class.java)
    }
    @Provides
    @Singleton
    fun ProvideRepository (apiManager: ApiManager) : CategoriesRepository{
        return CategoriesRepositoryImpl(apiManager)

    }

}