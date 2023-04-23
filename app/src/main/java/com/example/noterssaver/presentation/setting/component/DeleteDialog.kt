package com.example.noterssaver.presentation.setting.component;

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.noterssaver.R

/*
 * Created by Shahid Iqbal on 4/21/2023.
 */

@Composable
fun DeleteDialog(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = stringResource(R.string.confirmation),
                style = LocalTextStyle.current.copy(fontWeight = FontWeight.Bold)
            )
        },
        text = {
            Text(text = stringResource(R.string.can_not_undone))
        },
        confirmButton = {
            TextButtonField(textId = R.string.delete) { onConfirm.invoke() }
        }, dismissButton = {
            TextButtonField(textId = R.string.cancel) { onCancel.invoke() }
        },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    )
}