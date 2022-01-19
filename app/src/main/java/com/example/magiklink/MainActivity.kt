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
     private lateinit var emailEditText: EditText
    private lateinit var smsEditText: EditText
    var email = String()
    var sms = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        emailEditText = findViewById(R.id.costEditText)
        smsEditText = findViewById(R.id.costEditText)
        
        magic = (applicationContext as MagiclinkInstance).magic
        magic.startRelayer(this)
    }

    fun emailAuthentication(view: View) {
          email = emailEditText.text.toString()

        val result = magic.auth.loginWithMagicLink(email)

        result.whenComplete { response: DIDToken?, error: Throwable? ->
            if (error != null) {
                //Handle Error
            }
            if (response != null && !response.hasError()) {
                Log.d("Magic", "You're logged in." + response.result)
                 intent = Intent(applicationContext, HomeActivity::class.java)  
                startActivity(intent) 
            } else {
                Log.d("login", "Not Logged in")
            }
        }
    }
    fun smsAuthtentication(view: View) {
        sms = emailEditText.text.toString()
        //please introspect the line below
        val configuration = LoginWithSMSConfiguration(phoneNumber.text.toString())
        val result = magic.auth.loginWithSMS(configuration)

        result.whenComplete { response: DIDToken?, error: Throwable? ->
            if (error != null) {
                //Handle Error
            }
            if (response != null && !response.hasError()) {
                Log.d("Magic", "You're logged in." + response.result)
                intent = Intent(applicationContext, HomeActivity::class.java)  
                startActivity(intent)  
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
