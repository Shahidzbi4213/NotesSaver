package com.example.noterssaver.presentation.setting.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.noterssaver.presentation.setting.model.SettingOption


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleSettingItem(option: SettingOption, onClick: () -> Unit) {

    Card(onClick = onClick) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Icon(
                painter = painterResource(id = option.icon),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .padding(10.dp),
            )

            Text(
                text = option.title, style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.weight(1f))

        }
    }
}
