package com.example.noterssaver.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.noterssaver.presentation.destinations.ShowNotesDestination
import com.example.noterssaver.ui.theme.NotesSaverTheme
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            NotesSaverTheme(dynamicColor = false) {

                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    startRoute = ShowNotesDestination,
                )
            }

        }
    }
}

