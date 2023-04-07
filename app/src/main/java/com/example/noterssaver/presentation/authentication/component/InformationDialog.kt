package com.example.noterssaver.presentation.authentication.component;

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.DialogProperties

/*
 * Created by Shahid Iqbal on 4/8/2023.
 */

@Composable
fun InformationDialog(
    message: String, onDismissRequest: () -> Unit,
    onConfirmClick: () -> Unit
) {

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = "Information",
                style = LocalTextStyle.current.copy(fontWeight = FontWeight.Bold)
            )
        },
        text = {
            Text(text = message)
        },
        confirmButton = {
            TextButton(
                onClick = onConfirmClick
            ) {
                Text(text = "Ok", fontWeight = FontWeight.Bold)
            }
        },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    )
}