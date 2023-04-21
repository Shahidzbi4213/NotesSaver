package com.example.noterssaver.presentation.authentication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.noterssaver.R
import com.example.noterssaver.presentation.authentication.utils.AuthState
import com.example.noterssaver.presentation.authentication.utils.BiometricPrompt.getBiometricPrompt
import com.example.noterssaver.presentation.authentication.utils.BiometricExistsStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/*
 * Created by Shahid Iqbal on 4/20/2023.
 */

class BiometricAuth(private val context: Context) {

    private val _authenticationState = MutableStateFlow<AuthState>(AuthState.Authenticating)
    val authenticationState: StateFlow<AuthState> get() = _authenticationState


    fun userCanAuthenticate(): BiometricExistsStatus {
        val biometricManager = BiometricManager.from(context)
        return when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {

            BiometricManager.BIOMETRIC_SUCCESS -> BiometricExistsStatus.DeviceBiometricEnabled
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> BiometricExistsStatus.Failure(
                context.getString(
                    R.string.not_available
                )
            )

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> BiometricExistsStatus.Failure(
                context.getString(
                    R.string.currently_unavailable
                )
            )

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> BiometricExistsStatus.Failure(
                context.getString(
                    R.string.setup_fingerprint
                )
            )

            else -> BiometricExistsStatus.Failure(context.getString(R.string.something_wrong))
        }
    }

    fun biometricAuthentication(
        activity: AppCompatActivity,
        isAuthStated: Boolean
    ) {
        if (!isAuthStated) {
            val executor = ContextCompat.getMainExecutor(activity)
            BiometricPrompt(
                activity,
                executor,
                object : BiometricPrompt.AuthenticationCallback() {

                    override fun onAuthenticationError(
                        errorCode: Int,
                        errString: CharSequence
                    ) {
                        super.onAuthenticationError(errorCode, errString)
                        _authenticationState.value = AuthState.AuthenticationFailed
                    }

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        _authenticationState.value = AuthState.Authenticated

                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        _authenticationState.value = AuthState.AuthenticationFailed
                    }
                }).authenticate(context.getBiometricPrompt())
        }
    }
}