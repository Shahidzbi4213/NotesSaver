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
fun SingleNoteItem(note: Note) {

    Card(
        colors = CardDefaults.cardColors(containerColor = Color(note.color)),
    ) {

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp),


        ) {
            Text(
                text = note.title, style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = note.content,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Justify
            )

            IconButton(
                onClick = {}, modifier = Modifier.align(Alignment.End)

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
