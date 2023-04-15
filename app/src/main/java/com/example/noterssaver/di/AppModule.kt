package com.example.noterssaver.di

import androidx.biometric.BiometricManager
import com.example.noterssaver.data.repository.NotesRepoImpl
import com.example.noterssaver.data.repository.SettingRepoImpl
import com.example.noterssaver.data.source.local.AppDatabase
import com.example.noterssaver.domain.repository.NotesRepo
import com.example.noterssaver.domain.repository.SettingRepo
import com.example.noterssaver.domain.usecase.notes.*
import com.example.noterssaver.domain.usecase.settings.*
import com.example.noterssaver.presentation.MainViewModel
import com.example.noterssaver.presentation.addnote.AddNoteViewModel
import com.example.noterssaver.presentation.setting.SettingViewModel
import com.example.noterssaver.presentation.shownotes.GetNotesViewModel
import com.example.noterssaver.app.worker.DeleteNotesWorker
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val appModule = module {

    //Main
    viewModel { MainViewModel() }

    //Notes
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

    worker { DeleteNotesWorker(get(), get(), get()) }

    //Setting
    single { get<AppDatabase>().settingDao() }
    single<SettingRepo> { SettingRepoImpl(get()) }
    single {
        SettingUseCases(
            updateAppLockerUseCase = UpdateAppLockUseCase(get()),
            updateThemeUseCase = UpdateThemeUseCase(get()),
            clearAllUseCase = ClearAllUseCase(get()),
            getCurrentThemeStatusUseCase = GetCurrentThemeStatusUseCase(get()),
            getAppLockStatusUseCase = GetAppLockStatusUseCase(get()),
            emptySettingUseCase = EmptySettingUseCase(get())
        )
    }

    single { BiometricManager.from(get()) }

    viewModel {
        SettingViewModel(get(), get())
    }
}