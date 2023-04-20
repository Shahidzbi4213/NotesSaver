package com.example.noterssaver.presentation.authentication.utils

sealed class BiometricExistsStatus {

    object Empty : BiometricExistsStatus()
    object DeviceBiometricEnabled : BiometricExistsStatus()
    data class Failure(val errorMessage: String) : BiometricExistsStatus()
}
