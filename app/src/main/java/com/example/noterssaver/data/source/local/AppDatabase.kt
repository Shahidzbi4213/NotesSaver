package com.example.noterssaver.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.noterssaver.data.model.Note
import com.example.noterssaver.data.model.Setting
import com.example.noterssaver.data.source.local.converters.OrderTypeConverter
import com.example.noterssaver.data.source.local.converters.ThemeStyleConverter

@Database(
    entities = [Note::class, Setting::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ThemeStyleConverter::class, OrderTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

    abstract fun settingDao(): SettingDao

    companion object {
        const val DATABASE_NAME = "app_db"
    }
}