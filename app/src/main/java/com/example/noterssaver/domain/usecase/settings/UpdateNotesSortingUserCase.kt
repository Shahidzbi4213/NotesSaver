package com.example.noterssaver.domain.usecase.settings

import com.example.noterssaver.domain.repository.NotesRepo
import com.example.noterssaver.domain.repository.SettingRepo
import com.example.noterssaver.presentation.setting.util.OrderType

/*
 * Created by Shahid Iqbal on 5/5/2023.
 */

class UpdateNotesSortingUserCase(private val repo: SettingRepo) {

    suspend operator fun invoke(orderType: OrderType) {
        repo.updateSortingOrder(orderType)
    }
}