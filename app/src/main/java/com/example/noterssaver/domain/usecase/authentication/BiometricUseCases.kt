package com.example.noterssaver.domain.usecase.authentication

data class BiometricUseCases(
    val biometricAvailability: BiometricAvailability,
    val biometricAuthentication: BiometricAuthentication,
    val biometricAuthenticationState: BiometricAuthenticationState
)
