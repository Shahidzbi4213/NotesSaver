package com.example.noterssaver.data.data_source

import androidx.room.TypeConverter
import com.example.noterssaver.presentation.setting.ThemeStyle

/*
 * Created by Shahid Iqbal on 4/1/2023.
 */

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
