package com.example.noterssaver.data.repository

import com.example.noterssaver.data.source.local.SettingDao
import com.example.noterssaver.data.model.Setting
import com.example.noterssaver.domain.repository.SettingRepo
import com.example.noterssaver.presentation.setting.util.OrderType
import com.example.noterssaver.presentation.setting.util.ThemeStyle
import kotlinx.coroutines.flow.Flow

class SettingRepoImpl(private val dao: SettingDao) : SettingRepo {

    override suspend fun insertEmpty(setting: Setting) {
        dao.insertEmpty(setting)
    }

    override suspend fun updateCurrentTheme(style: ThemeStyle) {
        dao.updateCurrentTheme(style)
    }

    override suspend fun updateAppLockStatus(value: Boolean) {
        dao.updateAppLockStatus(value)
    }

    override suspend fun updateSortingOrder(orderType: OrderType) {
        dao.updateSortingType(order = orderType)
    }


    override fun getCurrentTheme(): Flow<ThemeStyle> {
        return dao.getCurrentTheme()
    }

    override fun getAppLockStatus(): Flow<Boolean> {
        return dao.getAppLockStatus()
    }

    override fun getCurrentSortingOrder(): Flow<OrderType> {
        return dao.getCurrentSortingOrder()
    }
}