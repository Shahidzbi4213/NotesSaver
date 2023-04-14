package com.example.noterssaver.presentation.setting

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel

@Composable
fun currentAppTheme(vm: SettingViewModel = koinViewModel()): Boolean {
    val theme by vm.currentThemeState.collectAsState(ThemeStyle.LIGHT)
    return theme.let {
        when (it) {
            ThemeStyle.SYSTEM_DEFAULT -> isSystemInDarkTheme()
            ThemeStyle.LIGHT -> false
            ThemeStyle.DARK -> true
        }
    }
}

