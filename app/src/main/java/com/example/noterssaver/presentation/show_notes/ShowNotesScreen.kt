package com.example.noterssaver.presentation.show_notes

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
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.noterssaver.R
import com.example.noterssaver.presentation.MainViewModel
import com.example.noterssaver.presentation.components.MainScaffold
import com.example.noterssaver.presentation.destinations.AddNoteDestination
import com.example.noterssaver.util.Extensions.debug
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



    val notesList = viewModel.currentNotes.collectAsState().value
    val deleteState = viewModel.deleteState
    val copiedState = viewModel.copyClick

    val snackBarState = remember { SnackbarHostState() }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty))

    val lazyListState = rememberLazyListState()

    LaunchedEffect(key1 = Unit, block = {
        snapshotFlow { lazyListState.firstVisibleItemIndex }
            .collectLatest { index ->
                mainViewModel.updateScrollSate(index == 0)
            }
    })



    LaunchedEffect(key1 = copiedState, key2 = deleteState, block = {
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
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(5.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    state = lazyListState,
                ) {
                    items(items = notesList) {
                        SingleNoteItem(note = it)
                    }
                }
            }
        }

        if (isAddButtonClicked) navigator.navigate(AddNoteDestination)

    }


}
