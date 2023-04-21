package com.example.noterssaver.di

import com.example.noterssaver.data.repository.SettingRepoImpl
import com.example.noterssaver.data.source.local.AppDatabase
import com.example.noterssaver.domain.repository.SettingRepo
import com.example.noterssaver.domain.usecase.settings.ClearAllUseCase
import com.example.noterssaver.domain.usecase.settings.EmptySettingUseCase
import com.example.noterssaver.domain.usecase.settings.GetAppLockStatusUseCase
import com.example.noterssaver.domain.usecase.settings.GetCurrentThemeStatusUseCase
import com.example.noterssaver.domain.usecase.settings.SettingUseCases
import com.example.noterssaver.domain.usecase.settings.UpdateAppLockUseCase
import com.example.noterssaver.domain.usecase.settings.UpdateThemeUseCase
import com.example.noterssaver.presentation.setting.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/*
 * Created by Shahid Iqbal on 4/17/2023.
 */

val settingModule = module {

    single { get<AppDatabase>().settingDao() }
    single<SettingRepo> { SettingRepoImpl(get()) }

    single {
        SettingUseCases(
            updateAppLockerUseCase = UpdateAppLockUseCase(get()),
            updateThemeUseCase = UpdateThemeUseCase(get()),
            deleteAllNotesUseCase = ClearAllUseCase(get()),
            getCurrentThemeStatusUseCase = GetCurrentThemeStatusUseCase(get()),
            getAppLockStatusUseCase = GetAppLockStatusUseCase(get()),
            emptySettingUseCase = EmptySettingUseCase(get())
        )
    }

    viewModel { SettingViewModel(get()) }

}