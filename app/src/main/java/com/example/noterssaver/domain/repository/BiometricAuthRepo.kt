package com.example.noterssaver.domain.repository

import com.example.noterssaver.domain.utils.BiometricAuthResult

interface BiometricAuthRepo {

    suspend fun canAuthenticate():BiometricAuthResult
}