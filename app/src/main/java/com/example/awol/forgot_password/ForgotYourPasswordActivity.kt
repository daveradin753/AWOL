package com.example.awol.forgot_password

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.awol.R
import com.example.awol.login_signup.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class ForgotYourPasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_your_password)

        val etEmailForgotPassword = findViewById<EditText>(R.id.etEmailForgot)
        auth = FirebaseAuth.getInstance()

        findViewById<Button>(R.id.btnSendForgot).setOnClickListener {
            auth.sendPasswordResetEmail(etEmailForgotPassword.text.toString()).addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("FORGOT PASS", "Email sent.")
                    Toast.makeText(this, "Email Sent", Toast.LENGTH_SHORT).show()
                    intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}