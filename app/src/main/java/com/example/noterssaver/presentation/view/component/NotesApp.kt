package com.example.noterssaver.presentation.view.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.noterssaver.presentation.NavGraphs
import com.example.noterssaver.presentation.destinations.ShowNotesDestination
import com.ramcosta.composedestinations.DestinationsNavHost

/*
 * Created by Shahid Iqbal on 4/25/2023.
 */

@Composable
fun NotesApp(isScrollUp: Boolean) {
    val navController = rememberNavController()
    val snackHostState = remember { SnackbarHostState() }

    Scaffold(topBar = { TopAppBar(navController = navController) },
        snackbarHost = { SnackbarHost(hostState = snackHostState) },
        floatingActionButton = {
            FloatingButton(
                navController = navController, isScrollUp
            )
        }
    ) {

        DestinationsNavHost(
            navGraph = NavGraphs.root,
            startRoute = ShowNotesDestination,
            navController = navController,
            modifier = Modifier.padding(it)
        )
    }
}