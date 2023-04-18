package com.example.noterssaver.domain.usecase.authentication

import com.example.noterssaver.framework.authentication.BiometricExistRepo

/*
 * Created by Shahid Iqbal on 4/7/2023.
 */

class BiometricAvailability(private val repo: BiometricExistRepo) {

    suspend operator fun invoke() = repo.canAuthenticate()

}