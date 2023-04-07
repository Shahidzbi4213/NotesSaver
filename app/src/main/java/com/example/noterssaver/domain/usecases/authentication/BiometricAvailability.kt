package com.example.noterssaver.domain.usecases.authentication

import com.example.noterssaver.domain.repository.BiometricAuthRepo
import com.example.noterssaver.domain.utils.BiometricAuthResult

/*
 * Created by Shahid Iqbal on 4/7/2023.
 */

class BiometricAvailability(private val repo: BiometricAuthRepo) {


    suspend operator fun invoke() = repo.canAuthenticate()

}