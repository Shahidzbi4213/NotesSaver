package com.example.noterssaver.domain.usecase.settings

import com.example.noterssaver.domain.repository.SettingRepo
import com.example.noterssaver.presentation.setting.ThemeStyle
import kotlinx.coroutines.flow.Flow

class GetCurrentThemeStatusUseCase(private val repo: SettingRepo) {

    operator fun invoke(): Flow<ThemeStyle> {
        return repo.getCurrentTheme()
    }
}