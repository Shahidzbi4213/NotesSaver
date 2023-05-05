package com.example.noterssaver.data.source.local.converters

import androidx.room.TypeConverter
import com.example.noterssaver.presentation.setting.util.ThemeStyle

class ThemeStyleConverter {

    @TypeConverter
    fun fromThemeStyle(theme: ThemeStyle): String {
        return theme.name
    }

    @TypeConverter
    fun toThemeStyle(themeName: String): ThemeStyle {
        return try {
            ThemeStyle.valueOf(themeName)
        } catch (ex: IllegalArgumentException) {
            ThemeStyle.LIGHT // Default value if the stored value is invalid
        }
    }
}
