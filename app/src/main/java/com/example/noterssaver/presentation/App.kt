package com.example.noterssaver.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.noterssaver.presentation.components.Toolbar
import com.example.noterssaver.presentation.destinations.ShowNotesDestination
import com.example.noterssaver.presentation.viewmodels.NotesViewModel
import com.example.noterssaver.util.Extensions.debug
import com.ramcosta.composedestinations.DestinationsNavHost
import org.koin.androidx.compose.koinViewModel


// Created by Shahid Iqbal on 3/13/2023.

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {

    val navController = rememberNavController()
    val vm: NotesViewModel = koinViewModel()

    Scaffold(topBar = {
        Toolbar(navController = navController) {
            vm.updateClick()
        }
    }) {
        DestinationsNavHost(
            navGraph = NavGraphs.root,
            startRoute = ShowNotesDestination,
            modifier = Modifier.padding(it),
            navController = navController
        )
    }
}