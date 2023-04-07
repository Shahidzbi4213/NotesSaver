package com.example.noterssaver.presentation.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noterssaver.domain.usecases.settings.SettingUseCases
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/*
 * Created by Shahid Iqbal on 4/1/2023.
 */

class SettingViewModel(private val useCases: SettingUseCases) : ViewModel() {


    val currentThemeState = useCases.getCurrentThemeStatus.invoke()
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily, ThemeStyle.LIGHT
        )
    val appLockState =
        useCases.getAppLockStatus.invoke().stateIn(viewModelScope, SharingStarted.Lazily, false)


    init {
        viewModelScope.launch {
            useCases.emptySetting()
        }

    }

    fun updateTheme(value: ThemeStyle) = viewModelScope.launch {
        useCases.updateTheme(value)
    }


    fun updateAppLock(value: Boolean) = viewModelScope.launch {
        useCases.updateAppLocker.invoke(value)
    }

}