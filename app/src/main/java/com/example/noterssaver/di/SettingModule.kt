package com.example.noterssaver.di

import com.example.noterssaver.data.data_source.AppDatabase
import com.example.noterssaver.data.repository.SettingRepoImpl
import com.example.noterssaver.domain.repository.SettingRepo
import com.example.noterssaver.domain.usecases.settings.*
import com.example.noterssaver.presentation.setting.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object SettingModule {

    val settingModule = module {

        single { get<AppDatabase>().settingDao() }
        single<SettingRepo> { SettingRepoImpl(get()) }
        single {
            SettingUseCases(
                appLocker = AppLock(get()),
                updateTheme = UpdateTheme(get()),
                clearAll = ClearAll(get()),
                currentTheme = CurrentTheme(get()),
                emptySetting = EmptySetting(get())
            )
        }

        viewModel {
            SettingViewModel(get())
        }

    }

}