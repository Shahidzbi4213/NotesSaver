package com.example.noterssaver.di

import com.example.noterssaver.presentation.authentication.AuthenticationViewModel
import com.example.noterssaver.presentation.authentication.BiometricAuth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val biometricModule = module {


    single { BiometricAuth(get()) }
    viewModel { AuthenticationViewModel(get()) }
}