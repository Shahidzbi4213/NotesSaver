package com.example.noterssaver.di

import com.example.noterssaver.data.repository.BiometricAuthRepositoryImpl
import com.example.noterssaver.domain.repository.BiometricAuthRepo
import com.example.noterssaver.domain.usecases.authentication.BiometricAvailability
import com.example.noterssaver.presentation.authentication.AuthenticationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object BiometricModule {

    val biometricModule = module {

        single<BiometricAuthRepo> { BiometricAuthRepositoryImpl(get()) }
        single { BiometricAvailability(get()) }
        viewModel { AuthenticationViewModel(get()) }
    }
}