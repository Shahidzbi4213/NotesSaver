package com.example.noterssaver.presentation.show_notes

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noterssaver.R
import com.example.noterssaver.domain.model.Note
import com.example.noterssaver.ui.theme.RedOrange
import com.example.noterssaver.ui.theme.RedPink


// Created by Shahid Iqbal on 3/15/2023.

@Composable
fun SingleNoteItem(onDelete: () -> Unit) {

    Card(
        modifier = Modifier.padding(5.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
    ) {

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Save Password", style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = "Go hands-on and learn the fundamentals of declarative UI, working with state, layouts, and theming. You'll see what composables and modifiers are, how to work with basic UI elements such as Row and Column, and how to give state to your app.",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Justify
            )

            IconButton(
                onClick = onDelete, modifier = Modifier.align(Alignment.End)

            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    modifier = Modifier.graphicsLayer(
                        ambientShadowColor = Color.Black
                    )
                )
            }
        }
    }

}

@Preview
@Composable
fun ShowNoteItem() = SingleNoteItem() {}