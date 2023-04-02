package com.example.noterssaver.di

import androidx.room.Room
import com.example.noterssaver.data.data_source.AppDatabase
import com.example.noterssaver.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object MainModule {

    val mainModule = module {
        single {
            Room.databaseBuilder(get(), AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()
        }

        viewModel { MainViewModel() }
    }
}