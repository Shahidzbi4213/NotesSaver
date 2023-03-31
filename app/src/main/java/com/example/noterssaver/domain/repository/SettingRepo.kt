package com.example.noterssaver.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingRepo {

    suspend fun updateDarkMode(value: Boolean)
    suspend fun updateAppLockStatus(value: Boolean)
    suspend fun updatePassword(newPass: String)
    suspend fun updateHint(newHint: String)

    fun getCurrentPassword(): Flow<String>
    fun getCurrentHint(): Flow<String>
    fun getCurrentMode(): Flow<Boolean>
    fun getAppLockStatus(): Flow<Boolean>
}