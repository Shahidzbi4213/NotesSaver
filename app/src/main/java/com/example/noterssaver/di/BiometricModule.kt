package com.example.noterssaver.di

import androidx.biometric.BiometricManager
import com.example.noterssaver.domain.usecase.authentication.BiometricAuthentication
import com.example.noterssaver.domain.usecase.authentication.BiometricAuthenticationState
import com.example.noterssaver.framework.authentication.BiometricExistRepositoryImpl
import com.example.noterssaver.domain.usecase.authentication.BiometricAvailability
import com.example.noterssaver.domain.usecase.authentication.BiometricUseCases
import com.example.noterssaver.framework.authentication.BiometricAuthenticatorRepo
import com.example.noterssaver.framework.authentication.BiometricAuthenticatorRepoImpl
import com.example.noterssaver.framework.authentication.BiometricExistRepo
import com.example.noterssaver.presentation.MainActivity
import com.example.noterssaver.presentation.authentication.AuthenticationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val biometricModule = module {

    single { BiometricManager.from(get()) }

    single<BiometricExistRepo> { BiometricExistRepositoryImpl(get()) }
    single<BiometricAuthenticatorRepo> { BiometricAuthenticatorRepoImpl(get()) }

    single {
        BiometricUseCases(
            BiometricAvailability(get()),
            BiometricAuthentication(get()),
            BiometricAuthenticationState(get())
        )
    }

    viewModel { AuthenticationViewModel(get()) }
}