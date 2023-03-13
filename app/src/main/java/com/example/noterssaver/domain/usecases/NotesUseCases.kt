package com.example.noterssaver.domain.usecases


// Created by Shahid Iqbal on 3/13/2023.

data class NotesUseCases(
    val getNotes: GetNotes,
    val addNote: AddNote,
    val deleteNote: DeleteNote,
)
