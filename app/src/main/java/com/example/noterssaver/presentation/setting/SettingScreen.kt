package com.example.noterssaver.presentation.setting

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.noterssaver.domain.utils.OrderType
import com.example.noterssaver.presentation.MainViewModel
import com.example.noterssaver.presentation.components.MainScaffold
import com.example.noterssaver.presentation.destinations.ThemeScreenDestination
import com.example.noterssaver.presentation.setting.component.SettingOption
import com.example.noterssaver.util.Extensions.debug
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

    var menuClick by remember {
        mutableStateOf("")
    }

    viewModel.updateTitle("Settings")

    val currentTheme by settingViewModel.currentTheme.collectAsState(initial = ThemeStyle.LIGHT)

    MainScaffold {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .padding(top = 10.dp, start = 20.dp)
        ) {

            SettingOption(
                title = "Theme",
                subtitle = currentTheme.name,
                modifier = Modifier.fillMaxWidth()
            ) {
                menuClick = "Theme"
            }

            Spacer(modifier = Modifier.height(10.dp))

            SettingOption(
                title = "Fingerprint Lock",
                subtitle = "Disabled",
                modifier = Modifier.fillMaxWidth()
            ) {}
            Spacer(modifier = Modifier.height(10.dp))

            SettingOption(
                title = "Sorted By",
                subtitle = OrderType.ASCENDING.toString(),
                modifier = Modifier.fillMaxWidth()
            ) {}
            Spacer(modifier = Modifier.height(10.dp))

            SettingOption(
                title = "Clear All",
                subtitle = "Please be advised that all notes will be deleted. This action cannot be undone.",
                modifier = Modifier.fillMaxWidth()
            ) {}
        }
    }

    when (menuClick) {
        "Theme" -> {
            navigator.navigate(ThemeScreenDestination)

        }

    }
}





