package com.example.noterssaver.presentation.util

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

object Extensions {

    fun Any?.debug() = Log.d("Clean", "$this")

    suspend fun SnackbarHostState.snackBar(message: String) {
        coroutineScope {
            val job = launch {
                this@snackBar.showSnackbar(
                    message = message, duration = SnackbarDuration.Indefinite
                )
            }
            delay(1000L)
            job.cancel()
        }
    }

    fun Long.getDateTime(): String =
        DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a")
            .format(LocalDateTime.ofInstant(Instant.ofEpochMilli(this), ZoneId.systemDefault()))
}