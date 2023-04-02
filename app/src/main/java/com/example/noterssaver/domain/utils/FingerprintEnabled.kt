package com.example.noterssaver.domain.utils

/*
 * Created by Shahid Iqbal on 4/3/2023.
 */

/*
fun isEnabled(){
    val biometricManager = BiometricManager.from(this)
    when (biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
        BiometricManager.BIOMETRIC_SUCCESS ->
            Log.d("MY_APP_TAG", "App can authenticate using biometrics.")
        BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
            Log.e("MY_APP_TAG", "No biometric features available on this device.")
        BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
            Log.e("MY_APP_TAG", "Biometric features are currently unavailable.")
        BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
            // Prompts the user to create credentials that your app accepts.
            val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                    BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
            }
            startActivityForResult(enrollIntent, REQUEST_CODE)
        }
    }
}*/
