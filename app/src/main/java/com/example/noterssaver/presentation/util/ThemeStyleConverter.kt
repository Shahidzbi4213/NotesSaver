package com.example.noterssaver.presentation.util

import androidx.room.TypeConverter
import com.example.noterssaver.presentation.setting.util.ThemeStyle

class ThemeStyleConverter {

    @TypeConverter
    fun fromThemeStyle(settingsData: ThemeStyle): String {
        return settingsData.name
    }

    @TypeConverter
    fun toThemeStyle(value: String): ThemeStyle {
        return try {
            ThemeStyle.valueOf(value)
        } catch (ex: IllegalArgumentException) {
            ThemeStyle.LIGHT // Default value if the stored value is invalid
        }
    }
}
