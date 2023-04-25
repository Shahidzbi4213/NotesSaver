package com.example.noterssaver.presentation.view.component

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.noterssaver.presentation.NavGraphs
import com.example.noterssaver.presentation.appCurrentDestinationAsState
import com.example.noterssaver.presentation.startAppDestination

/*
 * Created by Shahid Iqbal on 4/25/2023.
 */

@Composable
fun NavController.currentAppDestination() = this.appCurrentDestinationAsState().value ?: NavGraphs.root.startAppDestination