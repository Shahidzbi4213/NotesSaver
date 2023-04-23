package com.example.noterssaver.presentation.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noterssaver.R
import com.example.noterssaver.presentation.MainViewModel
import com.example.noterssaver.presentation.destinations.BiometricScreenDestination
import com.example.noterssaver.presentation.setting.component.DeleteDialog
import com.example.noterssaver.presentation.setting.component.SingleSettingItem
import com.example.noterssaver.presentation.setting.component.ThemePickerDialog
import com.example.noterssaver.presentation.setting.model.SettingOption
import com.example.noterssaver.presentation.setting.util.ThemeStyle
import com.example.noterssaver.presentation.util.Extensions.snackBar
import com.example.noterssaver.presentation.view.component.MainScaffold
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
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
    val snackBarState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    var showThemePickerDialog by remember {
        mutableStateOf(false)
    }

    viewModel.updateTitle("Settings")

    MainScaffold(navigator = navigator, snackBarHost = {
        SnackbarHost(hostState = snackBarState)
    }) {
        LazyColumn(
            modifier = modifier.padding(it),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(5.dp)
        ) {

            itemsIndexed(
                SettingOption.SETTING_MENU,
                key = { _: Int, item: SettingOption -> item.icon }) { index, option ->
                val currentOption = if (index != 0) option else option.copy(
                    icon = updateSettingIconForTheme(
                        currentTheme = currentTheme
                    )
                )
                SingleSettingItem(
                    option = currentOption

                ) {
                    when (index) {
                        0 -> {
                            showThemePickerDialog = true
                        }

                        1 -> navigator.navigate(BiometricScreenDestination)
                        3 -> {
                            showDeleteDialog = true
                        }
                    }
                }
            }
        }
    }

    if (showDeleteDialog) DeleteDialog(onDismissRequest = { showDeleteDialog = false },
        onConfirm = {
            settingViewModel.deleteAllNotes()
            showDeleteDialog = false
            scope.launch {
                snackBarState.snackBar("Notes Cleared")
            }
        },
        onCancel = { showDeleteDialog = false })

    if (showThemePickerDialog) ThemePickerDialog(

        onDismissRequest = { showThemePickerDialog = false },
        onCancelRequest = { showThemePickerDialog = false },
        onConfirmRequest = {
            settingViewModel.updateTheme(it ?: ThemeStyle.LIGHT)
            showThemePickerDialog = false
        })
}

private fun updateSettingIconForTheme(
    currentTheme: ThemeStyle,
): Int {
    return when (currentTheme.ordinal) {
        0 -> R.drawable.light
        1 -> R.drawable.dark
        else -> R.drawable.auto
    }
}