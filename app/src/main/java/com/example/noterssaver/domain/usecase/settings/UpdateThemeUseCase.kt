package com.example.noterssaver.domain.usecase.settings

import com.example.noterssaver.domain.repository.SettingRepo
import com.example.noterssaver.presentation.setting.ThemeStyle

class UpdateThemeUseCase(private val repo: SettingRepo) {

    suspend operator fun invoke(value: ThemeStyle) {
        repo.updateCurrentTheme(value)
    }
}