package com.example.noterssaver.di

import androidx.room.Room
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import com.example.noterssaver.data.data_source.AppDatabase
import com.example.noterssaver.data.repository.NotesRepoImpl
import com.example.noterssaver.data.worker.DeleteNotesWorker
import com.example.noterssaver.domain.repository.NotesRepo
import com.example.noterssaver.domain.usecases.*
import com.example.noterssaver.presentation.MainViewModel
import com.example.noterssaver.presentation.add_note.AddNoteViewModel
import com.example.noterssaver.presentation.show_notes.GetNotesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module


// Created by Shahid Iqbal on 3/13/2023.

object Modules {

    val appModules = module {
        single {
            Room.databaseBuilder(get(), AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()
        }

        single { get<AppDatabase>().notesDao() }
        single<NotesRepo> { NotesRepoImpl(get()) }

        single {
            NotesUseCases(
                getNotes = GetNotes(get()),
                addNote = AddNote(get()),
                deleteNote = DeleteNote(get()),
                deleteOldNotes = DeleteOldNotes(get())
            )
        }

        viewModel { MainViewModel() }
        viewModel { AddNoteViewModel(get()) }
        viewModel { GetNotesViewModel(get()) }

        worker { DeleteNotesWorker(get(), get(), get()) }
    }
}