package com.example.noterssaver.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noterssaver.presentation.setting.util.ThemeStyle

@Entity
data class Setting(
    val currentTheme: ThemeStyle = ThemeStyle.LIGHT,
    val isAppLock: Boolean = false,
    val currentPassword: String? = null,
    val hint: String? = null,
    @PrimaryKey
    val key: Int = 1
)