package com.example.noterssaver.domain.usecase.settings

import com.example.noterssaver.data.model.Setting
import com.example.noterssaver.domain.repository.SettingRepo

class EmptySettingUseCase(private val repo: SettingRepo) {

    suspend operator fun invoke() {
        repo.insertEmpty(Setting())
    }
}