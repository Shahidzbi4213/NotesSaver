package com.example.noterssaver.presentation.setting.component

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
import com.example.noterssaver.presentation.view.component.TextButtonField
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

/*
 * Created by Shahid Iqbal on 5/5/2023.
 */


@Composable
fun <T : Enum<T>> PickerDialog(
    title: String,
    optionList: List<T>,
    currentState: StateFlow<T>,
    onConfirmRequest: (T) -> Unit,
    onDismissRequest: () -> Unit,
    onCancelRequest: () -> Unit,
) {

    var selectedValue by remember {
        mutableStateOf<T?>(null)
    }



    LaunchedEffect(key1 = Unit) {
        currentState.collectLatest {
            selectedValue = it
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
                text = title, style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                ), modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )

            LazyColumn(
                contentPadding = PaddingValues(5.dp),
            ) {
                items(optionList) { item ->

                    OptionRadioButton(title = item.name.lowercase()
                        .replaceFirstChar { it.uppercase() },
                        selected = selectedValue?.ordinal == item.ordinal,
                        onClick = { selectedValue = item })
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
                TextButtonField(textId = R.string.ok) { onConfirmRequest.invoke(selectedValue!!) }
            }

        }
    }
}