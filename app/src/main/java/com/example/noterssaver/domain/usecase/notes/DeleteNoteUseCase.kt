package com.example.noterssaver.domain.usecase.notes

import com.example.noterssaver.data.model.Note
import com.example.noterssaver.domain.repository.NotesRepo

class DeleteNoteUseCase(private val notesRepo: NotesRepo) {

    suspend operator fun invoke(note: Note) = notesRepo.deleteNote(note)
}
