package com.example.noterssaver.presentation.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noterssaver.domain.usecases.settings.SettingUseCases
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/*
 * Created by Shahid Iqbal on 4/1/2023.
 */

class SettingViewModel(private val useCases: SettingUseCases) : ViewModel() {



    val currentTheme: Flow<ThemeStyle> =
        useCases.currentTheme.invoke()

    init {
        viewModelScope.launch {
            useCases.emptySetting()
        }

    }

    fun updateTheme(value: ThemeStyle) {
        viewModelScope.launch {
            useCases.updateTheme(value)
        }

    }
}