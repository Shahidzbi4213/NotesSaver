package com.example.noterssaver.presentation.setting

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.noterssaver.presentation.MainViewModel
import com.example.noterssaver.presentation.components.MainScaffold
import com.example.noterssaver.presentation.setting.component.RadioOption
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.koinViewModel

/*
 * Created by Shahid Iqbal on 4/2/2023.
 */

@Destination
@Composable
fun ThemeScreen(
    settingViewModel: SettingViewModel = koinViewModel(),
    mainViewModel: MainViewModel = koinViewModel()
) {

    mainViewModel.updateTitle("Theme")

    val themes = ThemeStyle.values().toList()
    val currentTheme by settingViewModel.currentTheme.collectAsState(initial = ThemeStyle.LIGHT)


    MainScaffold { paddingValues ->

        LazyColumn(
            contentPadding = PaddingValues(10.dp),
            modifier = Modifier.padding(paddingValues)
        ) {
            items(themes) {
                RadioOption(title = it.name.lowercase().replaceFirstChar { char -> char.uppercase() },
                    selected = it.name == currentTheme.name,
                    onClick = { settingViewModel.updateTheme(it) })
            }
        }
    }
}
