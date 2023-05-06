package com.example.noterssaver.presentation.setting.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.noterssaver.R

data class SettingOption(
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    companion object {
        val SETTING_MENU = listOf(
            SettingOption(R.string.appearance, R.drawable.light),
            SettingOption(R.string.fingerprint, R.drawable.lock),
            SettingOption(R.string.sort_notes, R.drawable.sort),
            SettingOption(R.string.language, R.drawable.language),
            SettingOption(R.string.clear_notes, R.drawable.delete),
        )
    }
}

