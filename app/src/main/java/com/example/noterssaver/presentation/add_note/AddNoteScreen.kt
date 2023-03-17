package com.example.noterssaver.presentation.add_note

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.noterssaver.presentation.MainViewModel
import com.example.noterssaver.presentation.components.MainScaffold
import com.example.noterssaver.util.Extensions.snackBar
import com.example.noterssaver.util.NoteState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel


// Created by Shahid Iqbal on 3/13/2023.


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
fun AddNote(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    viewModel: AddNoteViewModel = koinViewModel(),
    mainViewModel: MainViewModel = koinViewModel(),
) {

    var title by remember {
        mutableStateOf("")
    }
    var detail by remember {
        mutableStateOf("")
    }



    val snackBarState = remember { SnackbarHostState() }
    val currentNoteState = viewModel.addEditState

    mainViewModel.updateTitle("Add Note")
    LaunchedEffect(key1 = currentNoteState, block = {
        currentNoteState?.let {
            when (it) {
                is NoteState.Error -> {
                    snackBarState.snackBar(it.error.message!!)
                }
                is NoteState.Success -> {
                    snackBarState.snackBar(it.success)
                    navigator.navigateUp()
                }
            }
        }

    })

    MainScaffold(floatingIcon = Icons.Default.Check, floatingButtonClick = {
        viewModel.onSavedClick(title, detail)
    },
        snackBarHost = { SnackbarHost(hostState = snackBarState) }) { paddingValues ->
        Column(
            modifier = modifier
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
                onValueChange = { title = it },
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.titleMedium,
                singleLine = true,
                placeholder = { Text(text = "Title Of Note") },
                colors = TextFieldDefaults.textFieldColors(
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
                value = detail,
                onValueChange = { detail = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 10.dp),
                placeholder = { Text(text = "Write here what u want to save.") },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
    }
}