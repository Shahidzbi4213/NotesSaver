package com.example.noterssaver.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.noterssaver.data.model.Note
import com.example.noterssaver.presentation.util.Extensions.debug
import com.example.noterssaver.presentation.util.MainStates

class MainViewModel : ViewModel() {

    var mainStates: MainStates by mutableStateOf(MainStates())
        private set

    fun updateCurrentEditableNote(note: Note) {
        mainStates = mainStates.copy(currentEditableNote = note)
    }

    fun updateScrollSate(newScrollState: Boolean) {
        mainStates = MainStates(isScrollUp = newScrollState)
    }

    fun clickForSaveNote(updateValue: Boolean) {
        mainStates = MainStates(onSaveClick = updateValue)
    }
}

