package com.example.noterssaver.di

import com.example.noterssaver.data.repository.NotesRepoImpl
import com.example.noterssaver.data.source.local.AppDatabase
import com.example.noterssaver.domain.repository.NotesRepo
import com.example.noterssaver.domain.usecase.notes.AddNoteUseCase
import com.example.noterssaver.domain.usecase.notes.ClearAllNotesUseCase
import com.example.noterssaver.domain.usecase.notes.DeleteNoteUseCase
import com.example.noterssaver.domain.usecase.notes.DeleteOldNotesUseCase
import com.example.noterssaver.domain.usecase.notes.GetNotesUseCase
import com.example.noterssaver.domain.usecase.notes.NotesUseCases
import com.example.noterssaver.presentation.addnote.AddNoteViewModel
import com.example.noterssaver.presentation.shownotes.GetNotesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


// Created by Shahid Iqbal on 3/13/2023.

val notesModule = module {


    single { get<AppDatabase>().notesDao() }

    single<NotesRepo> { NotesRepoImpl(get()) }


    single {
        NotesUseCases(
            getNotesUseCase = GetNotesUseCase(get()),
            addNoteUseCase = AddNoteUseCase(get()),
            deleteNoteUseCase = DeleteNoteUseCase(get()),
            deleteOldNotesUseCase = DeleteOldNotesUseCase(get()),
            clearAllNotesUseCase = ClearAllNotesUseCase(get())
        )
    }

    viewModel { AddNoteViewModel(get()) }
    viewModel { GetNotesViewModel(get()) }
}
