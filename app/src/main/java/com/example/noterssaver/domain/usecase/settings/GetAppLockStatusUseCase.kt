package com.example.noterssaver.domain.usecase.settings

import com.example.noterssaver.domain.repository.SettingRepo

class GetAppLockStatusUseCase(private val repo: SettingRepo) {

    operator fun invoke() = repo.getAppLockStatus()
}