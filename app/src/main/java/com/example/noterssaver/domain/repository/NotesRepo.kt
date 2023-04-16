package com.example.noterssaver.domain.repository

import com.example.noterssaver.data.model.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepo {

    fun getNotes(): Flow<List<Note>>
    suspend fun insertNotes(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun getNoteById(id: Int): Note?
    suspend fun deleteOldNotes()
    suspend fun deleteAllNotes()
}