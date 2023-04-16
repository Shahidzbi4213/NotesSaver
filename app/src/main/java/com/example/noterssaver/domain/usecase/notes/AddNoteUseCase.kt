package com.example.noterssaver.domain.usecase.notes

import com.example.noterssaver.data.model.InvalidNoteException
import com.example.noterssaver.data.model.Note
import com.example.noterssaver.domain.repository.NotesRepo

class AddNoteUseCase(private val repo: NotesRepo) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank())
            throw InvalidNoteException("Title can't be Empty")
        if (note.content.isBlank())
            throw InvalidNoteException("Content can't be Empty")
        repo.insertNotes(note)
    }
}