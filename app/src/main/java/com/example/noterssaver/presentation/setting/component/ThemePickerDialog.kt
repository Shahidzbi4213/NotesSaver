package com.example.noterssaver.presentation.setting.component;

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.noterssaver.R
import com.example.noterssaver.presentation.setting.SettingViewModel
import com.example.noterssaver.presentation.setting.util.ThemeStyle
import com.example.noterssaver.presentation.view.component.TextButtonField
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

/*
 * Created by Shahid Iqbal on 4/23/2023.
 */

@Composable
fun ThemePickerDialog(
     currentThemeState:StateFlow<ThemeStyle>,
    onDismissRequest: () -> Unit,
    onCancelRequest: () -> Unit,
    onConfirmRequest: (ThemeStyle?) -> Unit
) {

    val themes = ThemeStyle.values().toList()
    var selectedTheme by remember {
        mutableStateOf<ThemeStyle?>(null)
    }

    LaunchedEffect(key1 = Unit) {
        currentThemeState.collectLatest {
            selectedTheme = it
        }
    }

    Dialog(
        onDismissRequest = onDismissRequest,
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {

        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            shape = MaterialTheme.shapes.extraLarge,
        ) {

            Text(
                text = "Choose Theme", style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                ), modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
            )

            LazyColumn(
                contentPadding = PaddingValues(5.dp),
            ) {
                items(themes) { item ->
                    val name = when (item.ordinal) {
                        0 -> "Light"
                        1 -> "Dark"
                        else -> "System Default"
                    }
                    RadioOption(title = name,
                        selected = selectedTheme?.ordinal == item.ordinal,
                        onClick = { selectedTheme = item })
                }
            }


            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {

                TextButtonField(textId = R.string.cancel) { onCancelRequest.invoke() }
                Spacer(modifier = Modifier.width(5.dp))
                TextButtonField(textId = R.string.ok) { onConfirmRequest.invoke(selectedTheme) }
            }

        }

    }
}
