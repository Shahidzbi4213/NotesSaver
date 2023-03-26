package com.example.noterssaver.presentation.show_notes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.noterssaver.R
import com.example.noterssaver.presentation.MainViewModel
import com.example.noterssaver.presentation.components.MainScaffold
import com.example.noterssaver.presentation.destinations.AddNoteDestination
import com.example.noterssaver.util.Extensions.snackBar
import com.example.noterssaver.util.NoteState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest
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
    mainViewModel: MainViewModel = koinViewModel()
) {

    var isAddButtonClicked by remember {
        mutableStateOf(false)
    }
    val notesList by viewModel.currentNotes.collectAsState()
    val searchText by viewModel.searchText.collectAsState()

    val deleteState = viewModel.deleteState
    val copiedState = viewModel.copyClick


    val snackBarState = remember { SnackbarHostState() }
    val lazyListState = rememberLazyListState()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty))

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current


    LaunchedEffect(key1 = copiedState, key2 = deleteState, key3 = null, block = {
        if (copiedState) {
            snackBarState.snackBar("Copied to clipboard")
            viewModel.onCopied(false)
        }

        deleteState?.let {
            when (it) {
                is NoteState.Error -> snackBarState.showSnackbar(
                    message = it.error.localizedMessage ?: "Something Went Wrong"
                )
                is NoteState.Success -> {
                    snackBarState.showSnackbar(message = it.success)
                    viewModel.updateDeleteState()

                }
            }
        }

        snapshotFlow { lazyListState.firstVisibleItemIndex }.collectLatest { index ->
            mainViewModel.updateScrollSate(index == 0)
        }

    })


    MainScaffold(floatingIcon = Icons.Default.Add, floatingButtonClick = {
        isAddButtonClicked = true
    }, snackBarHost = { SnackbarHost(hostState = snackBarState) }) { paddingValues ->


        if (notesList.isEmpty()) LottieAnimation(
            composition,
            modifier.padding(paddingValues),
            iterations = LottieConstants.IterateForever
        )
        else {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .clickable {
                        focusManager.clearFocus()
                    }
            ) {

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = searchText,
                    onValueChange = { viewModel.onSearchTextChange(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                        .focusRequester(focusRequester),
                    textStyle = MaterialTheme.typography.titleMedium,
                    singleLine = true,
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    },
                    placeholder = { Text(text = "Search a note") },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn(
                    contentPadding = PaddingValues(5.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    state = lazyListState,
                ) {
                    items(items = notesList) {
                        SingleNoteItem(note = it, onEdit = {
                            navigator.navigate(AddNoteDestination(it))
                        })
                    }
                }
            }
        }

        if (isAddButtonClicked) navigator.navigate(AddNoteDestination(null))

    }


}
