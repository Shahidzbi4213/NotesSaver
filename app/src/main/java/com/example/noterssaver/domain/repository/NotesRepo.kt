package com.example.noterssaver.domain.repository

import com.example.noterssaver.domain.model.Note
import kotlinx.coroutines.flow.Flow


// Created by Shahid Iqbal on 3/13/2023.

interface NotesRepo {

    fun getNotes(): Flow<List<Note>>

    suspend fun insertNotes(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun getNoteById(id: Int): Note?
   suspend fun deleteOldNotes()

}