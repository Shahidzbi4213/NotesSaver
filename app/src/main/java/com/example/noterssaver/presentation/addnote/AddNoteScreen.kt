package com.example.noterssaver.presentation.addnote

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.noterssaver.data.model.Note
import com.example.noterssaver.presentation.MainViewModel
import com.example.noterssaver.presentation.util.Extensions.snackBar
import com.example.noterssaver.presentation.util.NoteState
import com.example.noterssaver.presentation.view.component.MainScaffold
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination
fun AddNote(
    note: Note?,
    navigator: DestinationsNavigator,
    viewModel: AddNoteViewModel = koinViewModel(),
    mainViewModel: MainViewModel = koinViewModel()
) {

    val snackBarState = remember { SnackbarHostState() }
    val currentNoteState = viewModel.addEditState
    val title = viewModel.title
    val content = viewModel.content

    mainViewModel.updateTitle("Add Note")
    LaunchedEffect(key1 = currentNoteState, key2 = note, block = {
        currentNoteState?.let {
            when (it) {
                is NoteState.Error -> snackBarState.snackBar(it.error.message!!)
                is NoteState.Success -> navigator.navigateUp()

            }
        }

        note?.let {
            with(viewModel) {
                updateCurrentEditableNote(it)
                onTitleChange(it.title)
                onContentChange(it.content)
            }
        }
    })

    MainScaffold(floatingIcon = Icons.Default.Check, floatingButtonClick = {
        viewModel.saveNote()

    }, snackBarHost = { SnackbarHost(hostState = snackBarState) }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(10.dp),
        ) {
            Text(
                text = "Title",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(3.dp))

            TextField(
                value = title,
                onValueChange = { viewModel.onTitleChange(it) },
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.titleMedium,
                singleLine = true,
                placeholder = { Text(text = "Title Of Note") },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent

                )
            )

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "Detail About Note",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(3.dp))

            TextField(
                value = content,
                onValueChange = { viewModel.onContentChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 10.dp),
                placeholder = { Text(text = "Write here what u want to save.") },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                )
            )
        }
    }
}