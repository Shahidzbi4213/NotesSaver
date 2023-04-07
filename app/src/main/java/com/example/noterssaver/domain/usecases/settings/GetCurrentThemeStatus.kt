package com.example.noterssaver.domain.usecases.settings

import com.example.noterssaver.domain.repository.SettingRepo
import com.example.noterssaver.presentation.setting.ThemeStyle
import kotlinx.coroutines.flow.Flow

/*
 * Created by Shahid Iqbal on 4/1/2023.
 */

class GetCurrentThemeStatus(private val repo: SettingRepo) {


    operator fun invoke(): Flow<ThemeStyle> {
        return repo.getCurrentTheme()
    }
}