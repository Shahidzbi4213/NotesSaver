package com.example.noterssaver.presentation.view.component

import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight

/*
 * Created by Shahid Iqbal on 4/23/2023.
 */

@Composable
fun TextButtonField(@StringRes textId: Int, modifier: Modifier = Modifier, onClick: () -> Unit) {

    TextButton(
        onClick = onClick, modifier = modifier
    ) {
        Text(text = stringResource(textId), fontWeight = FontWeight.Bold)
    }

}