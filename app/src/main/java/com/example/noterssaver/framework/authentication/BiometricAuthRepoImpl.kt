package com.example.noterssaver.framework.authentication

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import com.example.noterssaver.R
import com.example.noterssaver.framework.util.BiometricAuthResult
import com.example.noterssaver.framework.util.BiometricAuthResult.Failure
import com.example.noterssaver.framework.util.BiometricAuthResult.UserCanAuthenticate

/*
 * Created by Shahid Iqbal on 4/7/2023.
 */

class BiometricAuthRepositoryImpl(private val context: Context) : BiometricAuthRepo {

    override suspend fun canAuthenticate(): BiometricAuthResult {
        val biometricManager = BiometricManager.from(context)
        return when (biometricManager.canAuthenticate(BIOMETRIC_STRONG)) {

            BiometricManager.BIOMETRIC_SUCCESS -> UserCanAuthenticate
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> Failure(
                context.getString(
                    R.string.not_available
                )
            )

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> Failure(
                context.getString(
                    R.string.currently_unavailable
                )
            )

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> Failure(
                context.getString(
                    R.string.setup_fingerprint
                )
            )

            else -> Failure(context.getString(R.string.something_wrong))
        }
    }
}

