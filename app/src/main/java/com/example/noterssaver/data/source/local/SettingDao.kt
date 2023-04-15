package com.example.noterssaver.data.source.local

import androidx.room.*
import com.example.noterssaver.data.model.Setting
import com.example.noterssaver.presentation.setting.ThemeStyle
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEmpty(setting: Setting = Setting())

    @Query("SELECT currentPassword FROM setting")
    fun getCurrentPassword(): Flow<String>

    @Query("SELECT hint FROM setting")
    fun getCurrentHint(): Flow<String>

    @Query("SELECT currentTheme FROM setting")
    fun getCurrentTheme(): Flow<ThemeStyle>

    @Query("SELECT isAppLock FROM setting")
    fun getAppLockStatus(): Flow<Boolean>

    @Query("UPDATE setting set currentTheme = :value")
    suspend fun updateCurrentTheme(value: ThemeStyle)

    @Query("UPDATE setting set isAppLock = :value")
    suspend fun updateAppLockStatus(value: Boolean)

    @Query("UPDATE setting set currentPassword = :newPass")
    suspend fun updatePassword(newPass: String)

    @Query("UPDATE setting set hint = :newHint")
    suspend fun updateHint(newHint: String)
}
