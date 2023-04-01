package com.example.noterssaver.presentation.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.noterssaver.domain.utils.OrderType
import com.example.noterssaver.presentation.MainViewModel
import com.example.noterssaver.presentation.components.MainScaffold
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
    viewModel: MainViewModel = koinViewModel()
) {

    var menuClick by remember {
        mutableStateOf("")
    }

    viewModel.updateTitle("Settings")

    MainScaffold {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .padding(top = 10.dp, start = 20.dp)
        ) {
            SettingOption(
                title = "Theme",
                subtitle = ThemeStyle.LIGHT.toString(),
                modifier = Modifier.fillMaxWidth()
            ) {
                menuClick = "Theme"
            }

            SettingOption(
                title = "Fingerprint Lock",
                subtitle = "Disabled",
                modifier = Modifier.fillMaxWidth()
            ) {}
            SettingOption(
                title = "Sorted By",
                subtitle = OrderType.ASCENDING.toString(),
                modifier = Modifier.fillMaxWidth()
            ) {}

            SettingOption(
                title = "Clear All",
                subtitle = "Please be advised that all notes will be deleted. This action cannot be undone.",
                modifier = Modifier.fillMaxWidth()
            ) {}
        }
    }

}


@Composable
fun SettingOption(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clickable { onClick.invoke() }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = subtitle,
                modifier = Modifier.padding(start = 2.dp),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}




