package com.example.noterssaver.di

import com.example.noterssaver.data.data_source.AppDatabase
import com.example.noterssaver.data.repository.NotesRepoImpl
import com.example.noterssaver.data.worker.DeleteNotesWorker
import com.example.noterssaver.domain.repository.NotesRepo
import com.example.noterssaver.domain.usecases.notes.*
import com.example.noterssaver.presentation.add_note.AddNoteViewModel
import com.example.noterssaver.presentation.show_notes.GetNotesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module


// Created by Shahid Iqbal on 3/13/2023.

object NotesModule {

    val notesModule = module {


        single { get<AppDatabase>().notesDao() }

        single<NotesRepo> { NotesRepoImpl(get()) }


        single {
            NotesUseCases(
                getNotes = GetNotes(get()),
                addNote = AddNote(get()),
                deleteNote = DeleteNote(get()),
                deleteOldNotes = DeleteOldNotes(get()),
                clearAllNotes = ClearAllNotes(get())
            )
        }

        viewModel { AddNoteViewModel(get()) }
        viewModel { GetNotesViewModel(get()) }

        worker { DeleteNotesWorker(get(), get(), get()) }
    }
}