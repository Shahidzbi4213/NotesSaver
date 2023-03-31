package com.example.noterssaver.domain.usecases.settings

import com.example.noterssaver.domain.repository.SettingRepo

/*
 * Created by Shahid Iqbal on 3/31/2023.
 */

class AppLock(private val repo: SettingRepo) {

    suspend operator fun invoke(value: Boolean) {
        repo.updateAppLockStatus(value)
    }
}