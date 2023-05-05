package com.example.noterssaver.domain.usecase.settings

data class SettingUseCases(
    val updateAppLockerUseCase: UpdateAppLockUseCase,
    val updateThemeUseCase: UpdateThemeUseCase,
    val updateNotesSortingUserCase: UpdateNotesSortingUserCase,
    val deleteAllNotesUseCase: ClearAllUseCase,
    val getCurrentThemeStatusUseCase: GetCurrentThemeStatusUseCase,
    val getAppLockStatusUseCase: GetAppLockStatusUseCase,
    val getCurrentSortingOrderUserCase: GetCurrentSortingOrderUserCase,
    val emptySettingUseCase: EmptySettingUseCase
)
