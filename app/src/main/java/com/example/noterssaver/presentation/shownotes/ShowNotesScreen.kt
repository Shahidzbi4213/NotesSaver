package com.example.noterssaver.presentation.shownotes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.noterssaver.R
import com.example.noterssaver.presentation.MainViewModel
import com.example.noterssaver.presentation.addnote.AddNoteViewModel
import com.example.noterssaver.presentation.destinations.AddNoteDestination
import com.example.noterssaver.presentation.destinations.SettingScreenDestination
import com.example.noterssaver.presentation.util.Extensions.snackBar
import com.example.noterssaver.presentation.util.NoteState
import com.example.noterssaver.presentation.view.component.MainScaffold
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Composable
@Destination
@RootNavGraph(start = true)
fun ShowNotes(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    viewModel: GetNotesViewModel = koinViewModel(),
    addNoteViewModel: AddNoteViewModel = koinViewModel(),
    mainViewModel: MainViewModel = koinViewModel(),
) {
    val notesList by viewModel.currentNotes.collectAsStateWithLifecycle()
    val searchText by viewModel.searchText.collectAsStateWithLifecycle()
    val lastDeletedNotes by viewModel.lastDeleteNote.collectAsStateWithLifecycle()

    val deleteState = viewModel.deleteState
    val copiedState = viewModel.copyClickState

    val snackBarState = remember { SnackbarHostState() }
    val lazyListState = rememberLazyListState()
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty))


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
                    snackBarState.showSnackbar(message = it.success, actionLabel = "Undo").apply {
                        if (this == SnackbarResult.ActionPerformed) addNoteViewModel.saveNote(
                            lastDeletedNotes
                        )

                    }
                    viewModel.updateDeleteState()
                }
            }
        }
    })

    MainScaffold(
        navigator = navigator,
        floatingIcon = Icons.Default.Add,
        floatingButtonClick = { navigator.navigate(AddNoteDestination(null)) },
        snackBarHost = { SnackbarHost(hostState = snackBarState) },
        onSettingClick = { navigator.navigate(SettingScreenDestination) }) { paddingValues ->

        if (notesList.isEmpty()) LottieAnimation(
            composition,
            modifier.padding(paddingValues),
            iterations = LottieConstants.IterateForever
        )
        else {
            Column(modifier = modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .clickable {
                    focusManager.clearFocus()
                })
            {
                Spacer(modifier = Modifier.height(10.dp))

                Searchbar(
                    searchText = searchText,
                    focusRequester = focusRequester,
                    onSearchTextChange = viewModel::onSearchTextChange
                )

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn(
                    contentPadding = PaddingValues(5.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    state = lazyListState,
                ) {
                    items(items = notesList, key = { it.timestamp }) {
                        SingleNoteItem(note = it, navigator)
                    }
                }
            }
        }
    }

    UpdateScrollState(mainViewModel, lazyListState)
}

/**
 * Update the scroll state of the app based on the
 * first visible item index of a LazyColumn composable
using the derivedStateOf function.
 * */

@Composable
private fun UpdateScrollState(
    mainViewModel: MainViewModel,
    lazyListState: LazyListState
) {
    remember {
        derivedStateOf {
            // This expression uses the lazyListState to determine the scroll state.
            // It checks whether the first visible item is beyond the first item or not.
            // If it is, then the scroll state is updated to indicate that the user is scrolling.
            mainViewModel.updateScrollSate(lazyListState.firstVisibleItemIndex > 0)
        }
    }

}