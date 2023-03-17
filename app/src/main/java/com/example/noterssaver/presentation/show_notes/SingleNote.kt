package com.example.noterssaver.presentation.show_notes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.noterssaver.domain.model.Note
import org.koin.androidx.compose.koinViewModel


// Created by Shahid Iqbal on 3/15/2023.

@Composable
fun SingleNoteItem(note: Note, viewModel: GetNotesViewModel = koinViewModel()) {

    Card() {

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            Text(
                text = note.title, style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = note.content,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Justify
            )

            IconButton(
                onClick = {
                    viewModel.onDelete(note)
                }, modifier = Modifier.align(Alignment.End)

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
