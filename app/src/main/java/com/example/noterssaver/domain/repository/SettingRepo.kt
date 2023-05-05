package com.example.noterssaver.domain.repository

import com.example.noterssaver.data.model.Setting
import com.example.noterssaver.presentation.setting.util.OrderType
import com.example.noterssaver.presentation.setting.util.ThemeStyle
import kotlinx.coroutines.flow.Flow

interface SettingRepo {

    suspend fun insertEmpty(setting: Setting)

    suspend fun updateCurrentTheme(style: ThemeStyle)

    suspend fun updateAppLockStatus(value: Boolean)

    suspend fun updateSortingOrder(orderType: OrderType)

    fun getCurrentTheme(): Flow<ThemeStyle>

    fun getAppLockStatus(): Flow<Boolean>

    fun getCurrentSortingOrder(): Flow<OrderType>
}