package com.example.noterssaver.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.noterssaver.domain.model.Note
import com.example.noterssaver.domain.model.Setting


// Created by Shahid Iqbal on 3/13/2023.

@Database(
    entities = [Note::class, Setting::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao
    abstract fun settingDao(): SettingDao

    companion object {
        const val DATABASE_NAME = "app_db"
    }
}