package com.example.noterssaver.data.source.local

import androidx.room.*
import com.example.noterssaver.data.model.Setting
import com.example.noterssaver.presentation.setting.util.OrderType
import com.example.noterssaver.presentation.setting.util.ThemeStyle
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEmpty(setting: Setting = Setting())

    @Query("SELECT currentTheme FROM setting")
    fun getCurrentTheme(): Flow<ThemeStyle>

    @Query("SELECT isAppLock FROM setting")
    fun getAppLockStatus(): Flow<Boolean>

    @Query("SELECT sortingOrder FROM setting")
    fun getCurrentSortingOrder(): Flow<OrderType>

    @Query("UPDATE setting set currentTheme = :theme")
    suspend fun updateCurrentTheme(theme: ThemeStyle)

    @Query("UPDATE setting set isAppLock = :value")
    suspend fun updateAppLockStatus(value: Boolean)

    @Query("UPDATE setting set sortingOrder = :order")
    suspend fun updateSortingType(order: OrderType)


}
