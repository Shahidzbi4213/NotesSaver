package com.example.noterssaver.presentation.shownotes

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.noterssaver.R
import com.example.noterssaver.data.model.Note
import com.example.noterssaver.presentation.util.Extensions.getDateTime
import com.example.noterssaver.presentation.view.component.CustomIconButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleNoteItem(
    note: Note,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onCopied: (Boolean) -> Unit

) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    val clipboardManager = LocalClipboardManager.current
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Card(onClick = {
        isExpanded = !isExpanded
        focusManager.clearFocus()
    }) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(10.dp)
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 250, easing = LinearOutSlowInEasing
                    )
                )
                .focusRequester(focusRequester)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
            ) {
                Text(
                    text = note.timestamp.getDateTime().substringAfter(" "),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = note.timestamp.getDateTime().substringBefore(" "),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Right
                )
            }

            Text(
                text = note.title, style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = note.content,
                style = MaterialTheme.typography.bodySmall,
                maxLines = if (isExpanded) Int.MAX_VALUE else 2,
                overflow = if (isExpanded) TextOverflow.Visible else TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = 1.dp)
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {

                CustomIconButton(
                    icon = Icons.Default.Edit,
                    onClick = onEdit
                )

                CustomIconButton(icon = R.drawable.ic_copy,
                    onClick = {
                        clipboardManager.setText(
                            AnnotatedString("${note.title} \n ${note.content}")
                        )

                        onCopied(true)
                    })

                CustomIconButton(
                    icon = Icons.Default.Delete,
                    onClick = onDelete
                )
            }
        }
    }
}

