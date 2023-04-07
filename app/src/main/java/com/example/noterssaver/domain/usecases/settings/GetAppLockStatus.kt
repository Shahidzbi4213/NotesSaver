package com.example.noterssaver.domain.usecases.settings

import com.example.noterssaver.domain.repository.SettingRepo

/*
 * Created by Shahid Iqbal on 4/7/2023.
 */

class GetAppLockStatus(private val repo: SettingRepo) {

     operator fun invoke() = repo.getAppLockStatus()

}