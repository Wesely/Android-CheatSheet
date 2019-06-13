Android BiometricPrompt : API 28
package com.test.mvvmplayground

import android.content.DialogInterface
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.support.v7.app.AppCompatActivity
import android.util.Log


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayBiometricPrompt()
    }

    private fun displayBiometricPrompt() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val callback = object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                    Log.d("Biometric", "Error")
                    super.onAuthenticationError(errorCode, errString)
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                    Log.d("Biometric", "Succeeded")
                    super.onAuthenticationSucceeded(result)
                }

                override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
                    Log.d("Biometric", "Help")
                    super.onAuthenticationHelp(helpCode, helpString)
                }

                override fun onAuthenticationFailed() {
                    Log.d("Biometric", "Failed")
                    super.onAuthenticationFailed()
                }
            }

            BiometricPrompt.Builder(baseContext)
                .setTitle("TITLE")
                .setDescription("DESCRIPTION")
                .setNegativeButton("cancel", mainExecutor,
                    DialogInterface.OnClickListener { dialog, which -> callback.onAuthenticationFailed() })
                .build().authenticate(CancellationSignal(), mainExecutor, callback)
        }
    }

}