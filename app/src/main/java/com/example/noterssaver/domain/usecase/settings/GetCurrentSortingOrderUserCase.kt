package com.example.noterssaver.domain.usecase.settings

import com.example.noterssaver.domain.repository.SettingRepo
import com.example.noterssaver.presentation.setting.util.OrderType
import kotlinx.coroutines.flow.Flow

/*
 * Created by Shahid Iqbal on 5/5/2023.
 */

class GetCurrentSortingOrderUserCase(private val repo: SettingRepo) {

    operator fun invoke(): Flow<OrderType> {
        return repo.getCurrentSortingOrder()
    }
}