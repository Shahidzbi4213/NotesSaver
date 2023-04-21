package com.example.noterssaver.domain.usecase.settings

data class SettingUseCases(
    val updateAppLockerUseCase: UpdateAppLockUseCase,
    val updateThemeUseCase: UpdateThemeUseCase,
    val deleteAllNotesUseCase: ClearAllUseCase,
    val getCurrentThemeStatusUseCase: GetCurrentThemeStatusUseCase,
    val getAppLockStatusUseCase: GetAppLockStatusUseCase,
    val emptySettingUseCase: EmptySettingUseCase
)
