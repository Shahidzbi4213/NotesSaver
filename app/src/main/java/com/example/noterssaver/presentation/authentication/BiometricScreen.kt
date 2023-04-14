package com.example.noterssaver.presentation.authentication

import androidx.biometric.BiometricManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noterssaver.R
import com.example.noterssaver.presentation.authentication.component.InformationDialog
import com.example.noterssaver.presentation.setting.SettingViewModel
import com.example.noterssaver.presentation.view.components.MainScaffold
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun BiometricScreen(
    settingViewModel: SettingViewModel = koinViewModel(),
    navigator: DestinationsNavigator
) {
    val context = LocalContext.current
    val appLockState by settingViewModel.appLockState.collectAsStateWithLifecycle()
    val snackBarState = remember { SnackbarHostState() }
    var showDialogFlag by remember {
        mutableStateOf(Pair(false, ""))
    }

    MainScaffold {
        Column(modifier = Modifier.padding(it)) {
            Row {
                Text(text = "Unlock with Fingerprint")
                Switch(checked = appLockState, onCheckedChange = {
                    if (appLockState) settingViewModel.updateAppLock(false)
                    else {
                        val biometricManager = BiometricManager.from(context)
                        when (biometricManager.canAuthenticate(
                            BiometricManager.Authenticators.BIOMETRIC_STRONG or
                                    BiometricManager.Authenticators.DEVICE_CREDENTIAL
                        )) {
                            BiometricManager.BIOMETRIC_SUCCESS -> {
                                settingViewModel.updateAppLock(true)
                            }
                            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                                showDialogFlag =
                                    Pair(true, context.getString(R.string.not_available))
                            }
                            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                                showDialogFlag =
                                    Pair(true, context.getString(R.string.currently_unavailable))
                            }
                            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                                showDialogFlag =
                                    Pair(true, context.getString(R.string.setup_fingerprint))
                            }
                            else -> {
                                showDialogFlag =
                                    Pair(true, context.getString(R.string.something_wrong))
                            }
                        }
                    }
                })
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
