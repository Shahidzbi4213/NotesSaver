package com.example.noterssaver.presentation.util

import com.example.noterssaver.data.model.Note

data class MainStates(
    val isScrollUp: Boolean = false,
    val onSaveClick: Boolean = false,
    val currentEditableNote: Note? = null
)