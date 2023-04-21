package com.example.noterssaver.presentation.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noterssaver.domain.usecase.settings.SettingUseCases
import com.example.noterssaver.presentation.setting.util.ThemeStyle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingViewModel(
    private val useCases: SettingUseCases
) : ViewModel() {

    val currentThemeState = useCases.getCurrentThemeStatusUseCase.invoke()
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily, ThemeStyle.LIGHT
        )
    val appLockState =
        useCases.getAppLockStatusUseCase.invoke()
            .stateIn(viewModelScope, SharingStarted.Lazily, false)

    init {
        viewModelScope.launch {
            useCases.emptySettingUseCase()
        }
    }


    fun updateTheme(value: ThemeStyle) = viewModelScope.launch {
        useCases.updateThemeUseCase(value)
    }

    fun updateAppLock(value: Boolean) = viewModelScope.launch {
        useCases.updateAppLockerUseCase.invoke(value)
    }

    fun deleteAllNotes() = viewModelScope.launch(Dispatchers.IO) {
        useCases.deleteAllNotesUseCase.invoke()
    }
}