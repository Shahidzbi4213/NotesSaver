package com.example.noterssaver.domain.repository

import com.example.noterssaver.data.model.Setting
import com.example.noterssaver.presentation.setting.ThemeStyle
import kotlinx.coroutines.flow.Flow

interface SettingRepo {

    suspend fun insertEmpty(setting: Setting)

    suspend fun updateCurrentTheme(style: ThemeStyle)

    suspend fun updateAppLockStatus(value: Boolean)

    suspend fun updatePassword(newPass: String)

    suspend fun updateHint(newHint: String)

    fun getCurrentPassword(): Flow<String>

    fun getCurrentHint(): Flow<String>

    fun getCurrentTheme(): Flow<ThemeStyle>

    fun getAppLockStatus(): Flow<Boolean>
}