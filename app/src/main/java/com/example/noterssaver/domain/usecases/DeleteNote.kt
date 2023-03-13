package com.example.noterssaver.domain.usecases

import com.example.noterssaver.domain.model.Note
import com.example.noterssaver.domain.repository.NotesRepo


// Created by Shahid Iqbal on 3/13/2023.

class DeleteNote(private val notesRepo: NotesRepo) {

    suspend operator fun invoke(note: Note) = notesRepo.deleteNote(note)
}
