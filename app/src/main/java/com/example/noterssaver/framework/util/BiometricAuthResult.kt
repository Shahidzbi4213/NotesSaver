package com.example.noterssaver.framework.util

sealed class BiometricAuthResult {

    object Empty : BiometricAuthResult()
    object UserCanAuthenticate : BiometricAuthResult()
    data class Failure(val errorMessage: String) : BiometricAuthResult()
}
