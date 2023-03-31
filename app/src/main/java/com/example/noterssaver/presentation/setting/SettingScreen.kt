package com.example.noterssaver.presentation.setting

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noterssaver.presentation.MainViewModel
import com.example.noterssaver.presentation.components.MainScaffold
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.koinViewModel

/*
 * Created by Shahid Iqbal on 3/31/2023.
 */

@Destination
@Composable
fun SettingScreen(modifier: Modifier = Modifier, viewModel: MainViewModel = koinViewModel()) {

    viewModel.updateTitle("Settings")

    MainScaffold {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .padding(start = 5.dp, end = 5.dp, top = 10.dp)
        ) {
            DarModeCard()
            Spacer(modifier = Modifier.height(15.dp))
            AppLockCard()
            Spacer(modifier = Modifier.height(15.dp))
            ClearAllNotesCard()
        }

    }

}

@Composable
fun DarModeCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
        ) {
            Text(
                text = "Dark Theme",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.weight(1f)
            )

            Switch(checked = true, onCheckedChange = {})
        }
    }
}

@Composable
fun AppLockCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
        ) {
            Text(
                text = "App Lock",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.weight(1f)
            )

            Switch(checked = true, onCheckedChange = {})
        }
    }
}

@Composable
fun ClearAllNotesCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
        ) {
            Text(
                text = "Clear All Notes",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.weight(1f)
            )

            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Clear")
            }
        }
    }
}

