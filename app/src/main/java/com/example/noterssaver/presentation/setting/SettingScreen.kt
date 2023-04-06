package com.example.noterssaver.presentation.setting

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.noterssaver.presentation.MainViewModel
import com.example.noterssaver.presentation.components.MainScaffold
import com.example.noterssaver.presentation.destinations.ThemeScreenDestination
import com.example.noterssaver.presentation.setting.component.SingleSettingItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

/*
 * Created by Shahid Iqbal on 3/31/2023.
 */

@Destination
@Composable
fun SettingScreen(
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = koinViewModel(),
    settingViewModel: SettingViewModel = koinViewModel()
) {


    viewModel.updateTitle("Settings")


    MainScaffold {

        LazyColumn(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(5.dp)
        ) {

            items(SettingOption.SETTING_MENU) { option ->
                SingleSettingItem(option = option) {

                    when (option.title) {
                        "Appearance" -> navigator.navigate(ThemeScreenDestination)
                    }
                }
            }

        }
    }

}





