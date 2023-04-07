package com.example.noterssaver.presentation.setting;

import androidx.biometric.BiometricViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noterssaver.R
import com.example.noterssaver.domain.utils.BiometricAuthResult
import com.example.noterssaver.presentation.authentication.AuthenticationViewModel
import com.example.noterssaver.presentation.authentication.component.InformationDialog
import com.example.noterssaver.presentation.components.MainScaffold
import com.example.noterssaver.util.Extensions.debug
import com.example.noterssaver.util.Extensions.snackBar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
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
            Row() {
                Text(text = "Unlock with Fingerprint")
                Switch(checked = appLockState, onCheckedChange = {

                    if (appLockState) settingViewModel.updateAppLock(false)
                    else {
                        when (biometricState) {
                            BiometricAuthResult.CanAuthenticate -> settingViewModel.updateAppLock(
                                true
                            )
                            BiometricAuthResult.Empty -> Unit
                            is BiometricAuthResult.Failure -> {
                                showDialogFlag = Pair(true, biometricState.errorMessage)
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
