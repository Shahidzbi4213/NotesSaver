package com.example.noterssaver.presentation.setting

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noterssaver.R
import com.example.noterssaver.presentation.destinations.BiometricScreenDestination
import com.example.noterssaver.presentation.setting.component.DeleteDialog
import com.example.noterssaver.presentation.setting.component.PickerDialog
import com.example.noterssaver.presentation.setting.component.SingleSettingItem
import com.example.noterssaver.presentation.setting.model.SettingOption
import com.example.noterssaver.presentation.setting.util.LANGUAGES
import com.example.noterssaver.presentation.setting.util.LanguageUtils
import com.example.noterssaver.presentation.setting.util.OrderType
import com.example.noterssaver.presentation.setting.util.ThemeStyle
import com.example.noterssaver.presentation.util.Extensions.debug
import com.example.noterssaver.presentation.util.Extensions.snackBar
import com.example.noterssaver.presentation.view.component.LocalSnackBarState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun SettingScreen(
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
    settingViewModel: SettingViewModel = koinViewModel()
) {

    val currentTheme by settingViewModel.currentThemeState.collectAsStateWithLifecycle(initialValue = ThemeStyle.LIGHT)
    val snackBarState = LocalSnackBarState.current
    val scope = rememberCoroutineScope()
    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    var showThemePickerDialog by remember {
        mutableStateOf(false)
    }

    var showSortingDialog by remember {
        mutableStateOf(false)
    }

    var showLanguageDialog by remember {
        mutableStateOf(false)
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(5.dp),
        modifier = modifier
    ) {

        itemsIndexed(SettingOption.SETTING_MENU,
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
                    0 -> showThemePickerDialog = true

                    1 -> navigator.navigate(BiometricScreenDestination)

                    2 -> showSortingDialog = true

                    3 -> showLanguageDialog = true

                    4 -> showDeleteDialog = true

                }
            }
        }
    }


    if (showDeleteDialog)
        DeleteDialog(onDismissRequest = { showDeleteDialog = false },
        onConfirm = {
            settingViewModel.deleteAllNotes()
            showDeleteDialog = false
            scope.launch {
                snackBarState.snackBar("Notes Cleared")
            }
        },
        onCancel = { showDeleteDialog = false })

    if (showThemePickerDialog)
        PickerDialog(title = stringResource(R.string.choose_theme),
        currentState = settingViewModel.currentThemeState.collectAsStateWithLifecycle().value,
        optionList = ThemeStyle.values().toList(),
        onDismissRequest = { showThemePickerDialog = false },
        onCancelRequest = { showThemePickerDialog = false },
        onConfirmRequest = {
            settingViewModel.updateTheme(it)
            showThemePickerDialog = false
        })


    if (showSortingDialog)
        PickerDialog(title = stringResource(id = R.string.sort_notes),
        optionList = OrderType.values().toList(),
        currentState = settingViewModel.currentSortingOrder.collectAsStateWithLifecycle().value,
        onConfirmRequest = {
            settingViewModel.updateCurrentSortingOrder(it)
            showSortingDialog = false
        },
        onDismissRequest = { showSortingDialog = false },
        onCancelRequest = { showSortingDialog = false })


    if (showLanguageDialog)
        PickerDialog(title = stringResource(R.string.select_language),
        optionList = LANGUAGES.values().toList(),
        currentState = LanguageUtils.currentLanguage(),
        onConfirmRequest = {
            LanguageUtils.setCurrentLanguage(it)
            showLanguageDialog = false
        },
        onDismissRequest = { showLanguageDialog = false },
        onCancelRequest = { showLanguageDialog = false })


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