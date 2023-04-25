package com.example.noterssaver.presentation.util

import com.example.noterssaver.R
import com.example.noterssaver.presentation.destinations.AddNoteDestination
import com.example.noterssaver.presentation.destinations.BiometricScreenDestination
import com.example.noterssaver.presentation.destinations.Destination
import com.example.noterssaver.presentation.destinations.SettingScreenDestination
import com.example.noterssaver.presentation.destinations.ShowNotesDestination

/*
 * Created by Shahid Iqbal on 4/25/2023.
 */

fun Destination?.topBarTitleId(): Int {
    return when (this) {
        is ShowNotesDestination -> R.string.app_name
        is AddNoteDestination -> R.string.add_note
        is SettingScreenDestination -> R.string.setting
        is BiometricScreenDestination -> R.string.fingerprint_lock
        else -> R.string.app_name
    }
}