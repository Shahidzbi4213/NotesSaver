package com.example.noterssaver.domain.usecase.settings

import com.example.noterssaver.domain.repository.SettingRepo

class UpdateAppLockUseCase(private val repo: SettingRepo) {

    suspend operator fun invoke(value: Boolean) {
        repo.updateAppLockStatus(value)
    }
}