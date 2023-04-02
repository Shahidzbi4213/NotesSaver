package com.example.noterssaver.data.data_source

import androidx.room.TypeConverter
import com.example.noterssaver.presentation.setting.ThemeStyle

/*
 * Created by Shahid Iqbal on 4/1/2023.
 */

class ThemeStyleConverter {
    @TypeConverter
    fun fromThemeStyle(themeStyle: ThemeStyle): String {
        return themeStyle.name
    }

    @TypeConverter
    fun toThemeStyle(value: String): ThemeStyle {
        return try {
            ThemeStyle.valueOf(value)
        } catch (ex: IllegalArgumentException) {
            ThemeStyle.AUTO // Default value if the stored value is invalid
        }
    }
}
