package com.example.noterssaver.domain.usecases.settings

import com.example.noterssaver.domain.repository.SettingRepo
import com.example.noterssaver.presentation.setting.ThemeStyle

/*
 * Created by Shahid Iqbal on 3/31/2023.
 */

class UpdateTheme(private val repo: SettingRepo) {

    suspend operator fun invoke(value: ThemeStyle) {
        repo.updateCurrentTheme(value)
    }
}