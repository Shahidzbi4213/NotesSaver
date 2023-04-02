package com.example.noterssaver.domain.model

import android.content.res.Resources.Theme
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noterssaver.presentation.setting.ThemeStyle

/*
 * Created by Shahid Iqbal on 3/31/2023.
 */

@Entity
data class Setting(
    val currentTheme: ThemeStyle = ThemeStyle.LIGHT,
    val isAppLock: Boolean = false,
    val currentPassword: String? = null,
    val hint: String? = null,
    @PrimaryKey
    val key: Int = 1
)