package com.example.noterssaver.framework.authentication

import com.example.noterssaver.framework.util.BiometricAuthResult

interface BiometricAuthRepo {

    suspend fun canAuthenticate(): BiometricAuthResult
}