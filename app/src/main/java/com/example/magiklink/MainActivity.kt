package com.example.magiklink

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import link.magic.android.Magic
import link.magic.android.modules.auth.AuthModule
import link.magic.android.modules.auth.response.DIDToken

class MainActivity : AppCompatActivity() {
    lateinit var magic: Magic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        magic = (applicationContext as MagiclinkInstance).magic
        magic.startRelayer(this)
    }

    fun emailAuthentication(view: View) {

        val result = magic.auth.loginWithMagicLink("hello@example.com")

        result.whenComplete { response: DIDToken?, error: Throwable? ->
            if (error != null) {
                //Handle Error
            }
            if (response != null && !response.hasError()) {
                Log.d("Magic", "You're logged in." + response.result)
            } else {
                Log.d("login", "Not Logged in")
            }
        }
    }
    fun smsAuthtentication(view: View) {

        val configuration = LoginWithSMSConfiguration(phoneNumber.text.toString())
        val result = magic.auth.loginWithSMS(configuration)

        result.whenComplete { response: DIDToken?, error: Throwable? ->
            if (error != null) {
                //Handle Error
            }
            if (response != null && !response.hasError()) {
                Log.d("Magic", "You're logged in." + response.result)
            } else {
                Log.d("login", "Not Logged in")
            }
        }

        }

    private fun LoginWithSMSConfiguration(toString: Any) {

    }

private fun Any.whenComplete(function: (DIDToken?, Throwable?) -> Int) {

}

private fun AuthModule.loginWithSMS(configuration: Any) {

}
    override fun onDestroy() {
        magic.destroyRelayer()
        super.onDestroy()
    }
}
