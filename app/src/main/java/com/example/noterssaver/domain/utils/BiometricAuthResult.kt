package com.example.noterssaver.domain.utils

sealed class BiometricAuthResult {

    object Empty : BiometricAuthResult()
    object CanAuthenticate : BiometricAuthResult()
    data class Failure(val errorMessage: String) : BiometricAuthResult()
}
