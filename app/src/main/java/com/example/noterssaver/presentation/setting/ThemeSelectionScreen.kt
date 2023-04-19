package com.example.noterssaver.presentation.setting

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noterssaver.presentation.MainViewModel
import com.example.noterssaver.presentation.setting.component.RadioOption
import com.example.noterssaver.presentation.view.component.MainScaffold
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun ThemeScreen(
    navigator: DestinationsNavigator,
    settingViewModel: SettingViewModel = koinViewModel(),
    mainViewModel: MainViewModel = koinViewModel()
) {
    mainViewModel.updateTitle("Theme")

    val themes = ThemeStyle.values().toList()
    val currentTheme by settingViewModel.currentThemeState.collectAsStateWithLifecycle(initialValue = ThemeStyle.LIGHT)

    MainScaffold(navigator = navigator) { paddingValues ->
        LazyColumn(
            contentPadding = PaddingValues(10.dp),
            modifier = Modifier.padding(paddingValues)
        ) {
            items(themes) { item ->
                val name = when (item.ordinal) {
                    0 -> "System Default"
                    2 -> "Dark"
                    else -> "Light"
                }
                RadioOption(title = name, selected = currentTheme.ordinal == item.ordinal,
                    onClick = { settingViewModel.updateTheme(item) })
            }
        }
    }
}

