package com.example.noterssaver.domain.usecases.notes


// Created by Shahid Iqbal on 3/13/2023.

data class NotesUseCases(
    val getNotes: GetNotes,
    val addNote: AddNote,
    val deleteNote: DeleteNote,
    val deleteOldNotes: DeleteOldNotes,
    val clearAllNotes: ClearAllNotes
)
