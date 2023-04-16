package com.example.noterssaver.framework.authentication

interface BiometricAuthRepo {

    suspend fun canAuthenticate(): BiometricAuthResult
}