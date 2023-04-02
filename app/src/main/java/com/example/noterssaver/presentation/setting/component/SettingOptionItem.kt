package com.example.noterssaver.presentation.setting.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingOption(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(start = 10.dp)
            .clickable { onClick.invoke() },
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = subtitle,
            modifier = Modifier.padding(start = 2.dp),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}
