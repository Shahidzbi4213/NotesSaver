package com.example.noterssaver.domain.usecases.settings

data class SettingUseCases(
    val appLocker: AppLock,
    val updateDarkMode: UpdateDarkMode,
    val clearAll: ClearAll,
    val currentTheme: CurrentTheme,
    val emptySetting: EmptySetting
)
