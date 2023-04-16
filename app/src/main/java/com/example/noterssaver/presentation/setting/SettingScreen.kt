package com.example.noterssaver.presentation.setting

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noterssaver.R
import com.example.noterssaver.presentation.MainViewModel
import com.example.noterssaver.presentation.destinations.BiometricScreenDestination
import com.example.noterssaver.presentation.destinations.ThemeScreenDestination
import com.example.noterssaver.presentation.setting.component.SingleSettingItem
import com.example.noterssaver.presentation.view.component.MainScaffold
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun SettingScreen(
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = koinViewModel(),
    settingViewModel: SettingViewModel = koinViewModel()
) {

    val currentTheme by settingViewModel.currentThemeState.collectAsStateWithLifecycle(initialValue = ThemeStyle.LIGHT)

    viewModel.updateTitle("Settings")

    MainScaffold {
        LazyColumn(
            modifier = modifier.padding(it),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(5.dp)
        ) {

            itemsIndexed(SettingOption.SETTING_MENU) { index, option ->
                val currentOption = if (index != 0) option else option.copy(
                    icon = updateSettingOptionIconForTheme(
                        currentTheme = currentTheme
                    )
                )
                SingleSettingItem(
                    option = currentOption

                ) {
                    when (index) {
                        0 -> navigator.navigate(ThemeScreenDestination)
                        1 -> navigator.navigate(BiometricScreenDestination)
                    }
                }
            }
        }
    }
}

@Composable
private fun updateSettingOptionIconForTheme(
    currentTheme: ThemeStyle,
): Int {
    return when (currentTheme.ordinal) {
        0 -> R.drawable.auto
        2 -> R.drawable.dark
        else -> R.drawable.light
    }
}