package com.example.noterssaver.presentation.view.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.noterssaver.presentation.MainActivity
import com.example.noterssaver.presentation.MainViewModel
import com.example.noterssaver.presentation.NavGraphs
import com.example.noterssaver.presentation.destinations.AddNoteDestination
import com.example.noterssaver.presentation.destinations.ShowNotesDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.qualifier.Qualifier

/*
 * Created by Shahid Iqbal on 4/25/2023.
 */

@Composable
fun NotesApp(mainViewModel: MainViewModel, activity: MainActivity) {

    val navController = rememberNavController()
    val snackBarState = remember { SnackbarHostState() }
    val isScrollUp = mainViewModel.mainStates.isScrollUp


    CompositionLocalProvider(
        LocalSnackBarState provides snackBarState,
    ) {
        Scaffold(
            topBar = { TopAppBar(navController = navController) },
            snackbarHost = { SnackbarHost(hostState = snackBarState) },
            floatingActionButton = {
                FloatingButton(
                    navController = navController, isScrollUp
                ) {
                    mainViewModel.clickForSaveNote(it)
                }
            }) {

            DestinationsNavHost(
                navGraph = NavGraphs.root,
                startRoute = ShowNotesDestination,
                modifier = Modifier.padding(it),
                navController = navController,
                dependenciesContainerBuilder = {
                    dependency(NavGraphs.root) {
                        val parentEntry = remember(navBackStackEntry) {
                            navController.getBackStackEntry(NavGraphs.root.route)
                        }
                        koinViewModel<MainViewModel>(viewModelStoreOwner = parentEntry)
                    }
                    dependency(koinViewModel<MainViewModel>(viewModelStoreOwner = activity))

                }
            )
        }
    }
}
