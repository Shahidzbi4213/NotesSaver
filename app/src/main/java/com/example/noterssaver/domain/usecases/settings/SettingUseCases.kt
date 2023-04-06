package com.example.noterssaver.domain.usecases.settings

data class SettingUseCases(
    val appLocker: AppLock,
    val updateTheme: UpdateTheme,
    val clearAll: ClearAll,
    val currentTheme: CurrentTheme,
    val emptySetting: EmptySetting
)
