package com.example.noterssaver.util

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


// Created by Shahid Iqbal on 3/15/2023.

object Extensions {


    fun Any?.debug() = Log.d("Clean", "$this")

    suspend fun SnackbarHostState.snackBar(message: String) {
        coroutineScope {
            val job = launch {
                this@snackBar.showSnackbar(message = message, duration = SnackbarDuration.Indefinite)
            }
            delay(1000L)
            job.cancel()
        }
    }
}