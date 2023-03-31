package com.example.noterssaver.data.data_source

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingDao {

    @Query("SELECT password FROM setting")
    fun getCurrentPassword(): Flow<String>

    @Query("SELECT hint FROM setting")
    fun getCurrentHint(): Flow<String>

    @Query("SELECT isDarkMode FROM setting")
    fun getCurrentMode(): Flow<Boolean>

    @Query("SELECT isAppLock FROM setting")
    fun getAppLockStatus(): Flow<Boolean>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDarkMode(value: Boolean)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAppLockStatus(value: Boolean)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePassword(newPass: String)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateHint(newHint: String)
}
