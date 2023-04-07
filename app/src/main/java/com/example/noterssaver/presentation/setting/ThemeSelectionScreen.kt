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

    val currentTheme by settingViewModel.currentThemeState.collectAsStateWithLifecycle(initialValue = ThemeStyle.LIGHT)


    MainScaffold { paddingValues ->

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

