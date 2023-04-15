package com.example.noterssaver.di

import androidx.room.Room
import com.example.noterssaver.data.source.local.AppDatabase
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()
    }
}