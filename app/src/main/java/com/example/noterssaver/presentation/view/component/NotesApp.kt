package com.example.noterssaver.presentation.view.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.noterssaver.presentation.NavGraphs
import com.example.noterssaver.presentation.destinations.ShowNotesDestination
import com.ramcosta.composedestinations.DestinationsNavHost

/*
 * Created by Shahid Iqbal on 4/25/2023.
 */

@Composable
fun NotesApp() {
    val navController = rememberNavController()
    val snackBarState = remember { SnackbarHostState() }


    CompositionLocalProvider(
        LocalSnackBarState provides snackBarState
    ) {
        Scaffold(
            topBar = { TopAppBar(navController = navController) },
            snackbarHost = { SnackbarHost(hostState = snackBarState) },
            floatingActionButton = { FloatingButton(navController = navController) }) {
            DestinationsNavHost(
                navGraph = NavGraphs.root,
                startRoute = ShowNotesDestination,
                modifier = Modifier.padding(it),
                navController = navController
            )
        }
    }
}
