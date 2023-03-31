package com.example.noterssaver.domain.usecases.notes

import com.example.noterssaver.domain.model.InvalidNoteException
import com.example.noterssaver.domain.model.Note
import com.example.noterssaver.domain.repository.NotesRepo


// Created by Shahid Iqbal on 3/13/2023.

class AddNote(private val repo: NotesRepo) {


    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank())
            throw InvalidNoteException("Title can't be Empty")
        if (note.content.isBlank())
            throw InvalidNoteException("Content can't be Empty")

        repo.insertNotes(note)
    }

}