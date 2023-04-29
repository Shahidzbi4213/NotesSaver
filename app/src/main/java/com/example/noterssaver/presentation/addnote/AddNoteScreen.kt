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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.noterssaver.R
import com.example.noterssaver.presentation.MainViewModel
import com.example.noterssaver.presentation.util.Extensions.snackBar
import com.example.noterssaver.presentation.util.NoteState
import com.example.noterssaver.presentation.view.component.LocalSnackBarState
import com.example.noterssaver.presentation.view.component.transparentTextFieldColors
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination
fun AddNote(
    vm: MainViewModel,
    navigator: DestinationsNavigator,
    viewModel: AddNoteViewModel = koinViewModel()
) {

    val snackBarState = LocalSnackBarState.current
    val focusRequester = remember { FocusRequester() }

    val onSave = vm.onSaveClick

    val currentNoteState = viewModel.addEditState
    val title = viewModel.title
    val content = viewModel.content


    SideEffects(focusRequester, currentNoteState, vm, snackBarState, navigator, onSave, viewModel)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(10.dp)
    ) {

        Spacer(modifier = Modifier.height(3.dp))

        TextField(
            value = title,
            onValueChange = viewModel::onTitleChange,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            singleLine = true,
            placeholder = {
                Text(
                    text = stringResource(id = R.string.title),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            colors = transparentTextFieldColors(),
            textStyle = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(5.dp))

        TextField(
            value = content,
            onValueChange = viewModel::onContentChange,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            placeholder = { Text(text = stringResource(R.string.note_something_down)) },
            colors = transparentTextFieldColors(),
            textStyle = MaterialTheme.typography.bodyLarge,
        )
    }


}

@Composable
private fun SideEffects(
    focusRequester: FocusRequester,
    currentNoteState: NoteState?,
    vm: MainViewModel,
    snackBarState: SnackbarHostState,
    navigator: DestinationsNavigator,
    onSave: Boolean,
    viewModel: AddNoteViewModel
) {
    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }

    LaunchedEffect(key1 = currentNoteState, block = {

        vm.clickForSaveNote(updateValue = false)

        when (currentNoteState) {
            is NoteState.Error -> snackBarState.snackBar(currentNoteState.error.message!!)
            is NoteState.Success -> {
                navigator.navigateUp()
            }

            else -> Unit
        }
    })

    LaunchedEffect(key1 = onSave, block = {
        if (onSave)
            viewModel.onEvent(NotesEvent.SaveNote)

    })

    LaunchedEffect(key1 = vm.editableNote, block = {
        if (vm.editableNote != null) {
            viewModel.onEvent(NotesEvent.EditNote(vm.editableNote!!))
        }
    })
}
