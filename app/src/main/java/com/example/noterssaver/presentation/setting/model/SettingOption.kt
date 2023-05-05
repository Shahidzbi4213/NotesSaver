package com.example.noterssaver.presentation.setting.model

import androidx.annotation.DrawableRes
import com.example.noterssaver.R

data class SettingOption(
    val title: String,
    @DrawableRes val icon: Int
) {
    companion object {
        val SETTING_MENU = listOf(
            SettingOption("Appearance", R.drawable.light),
            SettingOption("Fingerprint", R.drawable.lock),
            SettingOption("Sort Notes", R.drawable.sort),
            SettingOption("Language", R.drawable.language),
            SettingOption("Clear Notes", R.drawable.delete),
        )
    }
}

