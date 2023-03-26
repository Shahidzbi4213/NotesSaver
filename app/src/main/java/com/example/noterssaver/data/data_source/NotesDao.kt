package com.example.noterssaver.data.data_source

import androidx.room.*
import com.example.noterssaver.domain.model.Note
import kotlinx.coroutines.flow.Flow


// Created by Shahid Iqbal on 3/13/2023.

@Dao
interface NotesDao {


    @Query("Select * from note order by timestamp")
    fun getNotes(): Flow<List<Note>>

    @Upsert
    suspend fun insertNote(note: Note)

    @Query("Select * from note where id = :id")
    suspend fun getNotesById(id: Int): Note?

    @Delete
    suspend fun deleteNote(note: Note)

}