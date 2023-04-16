package com.example.noterssaver.presentation.authentication;

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noterssaver.R
import com.example.noterssaver.framework.util.BiometricAuthResult
import com.example.noterssaver.presentation.authentication.component.InformationDialog
import com.example.noterssaver.presentation.setting.SettingViewModel
import com.example.noterssaver.presentation.view.component.MainScaffold
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

/*
 * Created by Shahid Iqbal on 4/7/2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun BiometricScreen(
    settingViewModel: SettingViewModel = koinViewModel(),
    biometricViewModel: AuthenticationViewModel = koinViewModel(),
    navigator: DestinationsNavigator
) {

    val appLockState by settingViewModel.appLockState.collectAsStateWithLifecycle()
    val snackBarState = remember { SnackbarHostState() }
    val biometricState = biometricViewModel.biometricAvailabilityState
    var showDialogFlag by remember {
        mutableStateOf(Pair(false, ""))
    }

    MainScaffold {

        Column(modifier = Modifier.padding(it)) {
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(top = 5.dp, bottom = 5.dp)
            ) {
                Text(text = stringResource(R.string.unlock_with_fingerprint))
                Switch(
                    checked = appLockState,
                    onCheckedChange = {

                        if (appLockState) settingViewModel.updateAppLock(false)
                        else {
                            when (biometricState) {
                                BiometricAuthResult.UserCanAuthenticate -> settingViewModel.updateAppLock(
                                    true
                                )

                                BiometricAuthResult.Empty -> Unit
                                is BiometricAuthResult.Failure -> {
                                    showDialogFlag = Pair(true, biometricState.errorMessage)
                                }

                            }
                        }

                    },
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .height(5.dp)
                )

            }
            Text(text = stringResource(id = R.string.unlock_description))
        }
        Divider()
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
    Column {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .padding(top = 5.dp, bottom = 5.dp)
        ) {
            Text(
                text = stringResource(R.string.unlock_with_fingerprint),
                modifier = Modifier.weight(1f)
            )

            Switch(
                checked = false, onCheckedChange = {},
                modifier = Modifier
                    .padding(end = 10.dp)
                    .height(5.dp),
            )

        }
        Text(text = stringResource(id = R.string.unlock_description))
    }
    Divider()
}