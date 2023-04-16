package com.example.noterssaver.presentation.util

sealed class NoteState {

    data class Error(val error: Exception) : NoteState()
    data class Success(val success: String = "Notes Saved") : NoteState()
}
