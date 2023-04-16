package com.example.noterssaver.di

import androidx.biometric.BiometricManager
import com.example.noterssaver.data.repository.NotesRepoImpl
import com.example.noterssaver.data.repository.SettingRepoImpl
import com.example.noterssaver.data.source.local.AppDatabase
import com.example.noterssaver.domain.repository.NotesRepo
import com.example.noterssaver.domain.repository.SettingRepo
import com.example.noterssaver.domain.usecase.notes.AddNoteUseCase
import com.example.noterssaver.domain.usecase.notes.ClearAllNotesUseCase
import com.example.noterssaver.domain.usecase.notes.DeleteNoteUseCase
import com.example.noterssaver.domain.usecase.notes.DeleteOldNotesUseCase
import com.example.noterssaver.domain.usecase.notes.GetNotesUseCase
import com.example.noterssaver.domain.usecase.notes.NotesUseCases
import com.example.noterssaver.domain.usecase.settings.ClearAllUseCase
import com.example.noterssaver.domain.usecase.settings.EmptySettingUseCase
import com.example.noterssaver.domain.usecase.settings.GetAppLockStatusUseCase
import com.example.noterssaver.domain.usecase.settings.GetCurrentThemeStatusUseCase
import com.example.noterssaver.domain.usecase.settings.SettingUseCases
import com.example.noterssaver.domain.usecase.settings.UpdateAppLockUseCase
import com.example.noterssaver.domain.usecase.settings.UpdateThemeUseCase
import com.example.noterssaver.presentation.MainViewModel
import com.example.noterssaver.presentation.addnote.AddNoteViewModel
import com.example.noterssaver.presentation.setting.SettingViewModel
import com.example.noterssaver.presentation.shownotes.GetNotesViewModel
import com.example.noterssaver.worker.DeleteNotesWorker
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val appModule = module {

    viewModel { MainViewModel() }
    worker { DeleteNotesWorker(get(), get(), get()) }

}