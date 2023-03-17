package com.example.noterssaver.util

import com.example.noterssaver.domain.model.InvalidNoteException


// Created by Shahid Iqbal on 3/17/2023.

sealed class NoteState {

    data class Error(val error: Exception) : NoteState()
    data class Success(val success: String = "Notes Saved") : NoteState()
}
