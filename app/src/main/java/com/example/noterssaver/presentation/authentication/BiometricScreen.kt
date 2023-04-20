package com.example.noterssaver.presentation.authentication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noterssaver.R
import com.example.noterssaver.presentation.authentication.utils.BiometricExistsStatus
import com.example.noterssaver.presentation.MainViewModel
import com.example.noterssaver.presentation.authentication.component.InformationDialog
import com.example.noterssaver.presentation.setting.SettingViewModel
import com.example.noterssaver.presentation.view.component.MainScaffold
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

/*
 * Created by Shahid Iqbal on 4/7/2023.
 */

@Destination
@Composable
fun BiometricScreen(
    navigator: DestinationsNavigator,
    settingViewModel: SettingViewModel = koinViewModel(),
    biometricViewModel: AuthenticationViewModel = koinViewModel(),
    mainViewModel: MainViewModel = koinViewModel()
) {

    val appLockState by settingViewModel.appLockState.collectAsStateWithLifecycle()
    val biometricState = biometricViewModel.isBiometricExistAndEnabled

    var showDialogFlag by remember {
        mutableStateOf(Pair(false, ""))
    }
    mainViewModel.updateTitle("Fingerprint Lock")

    MainScaffold(navigator = navigator) { paddingValues ->

        Card(
            modifier = Modifier
                .padding(paddingValues)
                .padding(5.dp)
        ) {
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.unlock_with_fingerprint),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Text(
                        text = stringResource(id = R.string.unlock_description),
                        modifier = Modifier.padding(top = 2.dp)
                    )

                }

                Switch(
                    checked = appLockState,
                    onCheckedChange = {

                        if (appLockState) settingViewModel.updateAppLock(false)
                        else when (biometricState) {
                            BiometricExistsStatus.Empty -> Unit
                            BiometricExistsStatus.DeviceBiometricEnabled -> settingViewModel.updateAppLock(
                                true
                            )
                            is BiometricExistsStatus.Failure -> {
                                showDialogFlag = Pair(true, biometricState.errorMessage)
                            }
                        }


                    }
                )
            }
        }

    }

    if (showDialogFlag.first) {
        InformationDialog(message = showDialogFlag.second,
            onDismissRequest = { showDialogFlag = Pair(false, "") },
            onConfirmClick = { showDialogFlag = Pair(false, "") })
    }
}

@Preview(showBackground = true)
@Composable
private fun BiometricScreen() {
    Card(
        modifier = Modifier
            .padding(5.dp)
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.unlock_with_fingerprint),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    text = stringResource(id = R.string.unlock_description),
                    modifier = Modifier.padding(top = 1.dp)
                )

            }

            Switch(checked = true, onCheckedChange = {})
        }
    }
}