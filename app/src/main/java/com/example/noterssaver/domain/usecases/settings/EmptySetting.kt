package com.example.noterssaver.domain.usecases.settings

import com.example.noterssaver.domain.model.Setting
import com.example.noterssaver.domain.repository.SettingRepo

/*
 * Created by Shahid Iqbal on 4/1/2023.
 */

class EmptySetting(private val repo: SettingRepo) {

    suspend operator fun invoke() {
        repo.insertEmpty(Setting())
    }
}