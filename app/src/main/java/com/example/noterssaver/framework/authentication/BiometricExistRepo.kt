package com.example.noterssaver.framework.authentication

import com.example.noterssaver.framework.util.BiometricAuthResult

interface BiometricExistRepo {

    suspend fun canAuthenticate(): BiometricAuthResult
}