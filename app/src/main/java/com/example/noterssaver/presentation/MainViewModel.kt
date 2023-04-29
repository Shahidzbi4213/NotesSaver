package com.example.noterssaver.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.noterssaver.data.model.Note

class MainViewModel : ViewModel() {

    var isScrollUp: Boolean by mutableStateOf(false)
        private set

    var onSaveClick: Boolean by mutableStateOf(false)
        private set

    var editableNote: Note? by mutableStateOf(null)
        private set

    fun updateCurrentEditableNote(note: Note) {
        editableNote = note
    }

    fun updateScrollSate(newScrollState: Boolean) {
        isScrollUp = newScrollState
    }

    fun clickForSaveNote(updateValue: Boolean) {
        onSaveClick = updateValue
    }
}
