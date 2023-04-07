package com.example.noterssaver.domain.usecases.settings

data class SettingUseCases(
    val updateAppLocker: UpdateAppLock,
    val updateTheme: UpdateTheme,
    val clearAll: ClearAll,
    val getCurrentThemeStatus: GetCurrentThemeStatus,
    val getAppLockStatus: GetAppLockStatus,
    val emptySetting: EmptySetting
)
