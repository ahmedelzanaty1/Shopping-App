package com.example.shoppingapp.di

import com.example.shoppingapp.data.firebase.FireBaseOnlineDataSource
import com.example.shoppingapp.data.repository.auth.AuthRepositoryImpl
import com.example.shoppingapp.domain.repository.auth.AuthRepository
import com.example.shoppingapp.domain.use_cases.auth.GetCurrentUserUseCase
import com.example.shoppingapp.domain.use_cases.auth.LoginUseCase
import com.example.shoppingapp.domain.use_cases.auth.SignupUseCse
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseDataSource(firebaseAuth: FirebaseAuth): FireBaseOnlineDataSource {
        return FireBaseOnlineDataSource(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseDataSource: FireBaseOnlineDataSource): AuthRepository {
        return AuthRepositoryImpl(firebaseDataSource)
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(authRepository: AuthRepository): SignupUseCse {
        return SignupUseCse(authRepository)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCase {
        return LoginUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideGetCurrentUserUseCase(authRepository: AuthRepository): GetCurrentUserUseCase {
        return GetCurrentUserUseCase(authRepository)
    }
}