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

    /**
     * Deletes all notes from the database that are older than 1 month old.
     *
     * This method executes a SQL query that deletes all rows from the "notes" table
     * where the "timestamp"  is less than the current time minus 1 month.
     * The query is defined using the @Query annotation with the SQLite strftime()
     * function to calculate the timestamp threshold.
     */
    @Query("DELETE  FROM note WHERE timestamp < strftime('%s', 'now', '-1 month') * 1000")
    suspend fun deleteOldNotes()

}