package com.example.noterssaver.presentation.setting.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.noterssaver.presentation.setting.SettingViewModel
import com.example.noterssaver.presentation.setting.util.ThemeStyle
import org.koin.androidx.compose.koinViewModel

@Composable
fun currentAppTheme(vm: SettingViewModel): Boolean {
    val theme by vm.currentThemeState.collectAsState(ThemeStyle.LIGHT)
    return theme.let {
        when (it) {
            ThemeStyle.SYSTEM_DEFAULT -> isSystemInDarkTheme()
            ThemeStyle.LIGHT -> false
            ThemeStyle.DARK -> true
        }
    }
}

