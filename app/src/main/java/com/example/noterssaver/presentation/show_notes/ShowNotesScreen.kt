package com.example.noterssaver.presentation.show_notes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noterssaver.presentation.components.topAppBarColors
import com.example.noterssaver.presentation.destinations.AddNoteDestination
import com.example.noterssaver.presentation.note_app.MainScaffold
import com.example.noterssaver.util.Extensions.debug
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel


// Created by Shahid Iqbal on 3/13/2023.

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Destination
@RootNavGraph(start = true)
fun ShowNotes(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    viewModel: GetNotesViewModel = koinViewModel(),
) {

    var isAddButtonClicked by remember {
        mutableStateOf(false)
    }

    val notesList = viewModel.currentNotes.collectAsStateWithLifecycle().value

    MainScaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Notes Saver") }, colors = topAppBarColors())
        },
        floatingIcon = Icons.Default.Add,
        floatingButtonClick = {
            isAddButtonClicked = true
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            LazyColumn(
                contentPadding = PaddingValues(5.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(items = notesList) {
                    SingleNoteItem(note = it)
                }
            }
        }
    }

    if (isAddButtonClicked)
        navigator.navigate(AddNoteDestination)

}