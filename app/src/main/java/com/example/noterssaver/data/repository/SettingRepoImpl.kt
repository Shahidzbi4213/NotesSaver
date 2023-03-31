package com.example.noterssaver.data.repository

import com.example.noterssaver.data.data_source.SettingDao
import com.example.noterssaver.domain.repository.SettingRepo
import kotlinx.coroutines.flow.Flow

/*
 * Created by Shahid Iqbal on 3/31/2023.
 */

class SettingRepoImpl(private val dao: SettingDao) : SettingRepo {

    override suspend fun updateDarkMode(value: Boolean) {
        dao.updateDarkMode(value)
    }

    override suspend fun updateAppLockStatus(value: Boolean) {
        dao.updateAppLockStatus(value)
    }

    override suspend fun updatePassword(newPass: String) {
        dao.updatePassword(newPass)
    }

    override suspend fun updateHint(newHint: String) {
        dao.updateHint(newHint)
    }

    override fun getCurrentPassword(): Flow<String> {
        return dao.getCurrentPassword()
    }

    override fun getCurrentHint(): Flow<String> {
        return dao.getCurrentHint()
    }

    override fun getCurrentMode(): Flow<Boolean> {
        return dao.getCurrentMode()
    }

    override fun getAppLockStatus(): Flow<Boolean> {
        return dao.getAppLockStatus()
    }
}