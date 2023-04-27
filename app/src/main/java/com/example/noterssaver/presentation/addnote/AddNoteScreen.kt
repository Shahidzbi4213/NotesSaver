package com.example.noterssaver.presentation.addnote

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.noterssaver.R
import com.example.noterssaver.presentation.MainViewModel
import com.example.noterssaver.presentation.util.Extensions.debug
import com.example.noterssaver.presentation.util.Extensions.snackBar
import com.example.noterssaver.presentation.util.NoteState
import com.example.noterssaver.presentation.view.component.LocalSnackBarState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination
fun AddNote(
    navigator: DestinationsNavigator,
    viewModel: AddNoteViewModel = koinViewModel(),
    mainViewModel: MainViewModel = koinViewModel()
) {

    val snackBarState = LocalSnackBarState.current
    val currentNoteState = viewModel.addEditState
    val focusRequester = remember { FocusRequester() }

    val onSaved = mainViewModel.onSaveClick
    val title = viewModel.title
    val content = viewModel.content


    LaunchedEffect(key1 = Unit) { focusRequester.requestFocus() }
    LaunchedEffect(key1 = currentNoteState, block = {
        when (currentNoteState) {
            is NoteState.Error -> snackBarState.snackBar(currentNoteState.error.message!!)
            is NoteState.Success -> navigator.navigateUp()
            else -> Unit
        }
    })

    LaunchedEffect(key1 = onSaved, block = {
        if (onSaved) viewModel.onEvent(NotesEvent.SaveNote)
    })


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(10.dp)
    ) {
        Spacer(modifier = Modifier.height(3.dp))

        TextField(
            value = title,
            onValueChange = { viewModel.onTitleChange(it) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.title),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            colors = textFieldColors(),
            textStyle = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(5.dp))

        TextField(
            value = content,
            onValueChange = { viewModel.onContentChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .focusRequester(focusRequester),
            placeholder = { Text(text = stringResource(R.string.note_something_down)) },
            colors = textFieldColors(),
            textStyle = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Composable
private fun textFieldColors() = TextFieldDefaults.colors(
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    focusedContainerColor = Color.Transparent,
    unfocusedContainerColor = Color.Transparent
)