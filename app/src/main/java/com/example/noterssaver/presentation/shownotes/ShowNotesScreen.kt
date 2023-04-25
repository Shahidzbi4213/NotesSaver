package com.example.noterssaver.presentation.shownotes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.noterssaver.R
import com.example.noterssaver.presentation.MainViewModel
import com.example.noterssaver.presentation.addnote.AddNoteViewModel
import com.example.noterssaver.presentation.util.Extensions.snackBar
import com.example.noterssaver.presentation.util.NoteState
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

    val somethingWrong = stringResource(id = R.string.something_wrong)

    val canShowFloatButton by remember {
        derivedStateOf { lazyListState.firstVisibleItemIndex > 0 }
    }

    LaunchedEffect(key1 = copiedState) {

        if (copiedState) {
            snackBarState.snackBar("Copied to clipboard")
            viewModel.onCopied(false)
        }
    }
    LaunchedEffect(key1 = canShowFloatButton) {
        mainViewModel.updateScrollSate(canShowFloatButton)
    }
    LaunchedEffect(key1 = deleteState) {
        deleteState?.let {
            when (it) {
                is NoteState.Error -> snackBarState.showSnackbar(
                    message = it.error.localizedMessage ?: somethingWrong
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
    }

    if (notesList.isEmpty()) {
        LottieAnimation(
            composition,
            iterations = LottieConstants.IterateForever
        )
    } else {
        Column(modifier = modifier
            .fillMaxWidth()
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

