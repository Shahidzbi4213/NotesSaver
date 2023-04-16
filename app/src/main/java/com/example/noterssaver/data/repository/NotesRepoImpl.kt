package com.example.noterssaver.data.repository

import com.example.noterssaver.data.source.local.NotesDao
import com.example.noterssaver.data.model.Note
import com.example.noterssaver.domain.repository.NotesRepo
import kotlinx.coroutines.flow.Flow

class NotesRepoImpl(private val dao: NotesDao) : NotesRepo {

    override fun getNotes(): Flow<List<Note>> {
        return dao.getNotes()
    }

    override suspend fun insertNotes(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNotesById(id)
    }

    override suspend fun deleteOldNotes() {
        dao.deleteOldNotes()
    }

    override suspend fun deleteAllNotes() {
        dao.deleteAllNotes()
    }
}