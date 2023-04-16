package com.example.noterssaver.di

import androidx.biometric.BiometricManager
import com.example.noterssaver.framework.authentication.BiometricAuthRepositoryImpl
import com.example.noterssaver.domain.usecases.authentication.BiometricAvailability
import com.example.noterssaver.framework.authentication.BiometricAuthRepo
import com.example.noterssaver.presentation.authentication.AuthenticationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val biometricModule = module {

    single { BiometricManager.from(get()) }
    single<BiometricAuthRepo> { BiometricAuthRepositoryImpl(get()) }
    single { BiometricAvailability(get()) }
    viewModel { AuthenticationViewModel(get()) }
}