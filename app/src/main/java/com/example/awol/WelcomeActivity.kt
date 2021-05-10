package com.example.awol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.awol.login_signup.LoginActivity
import com.example.awol.login_signup.SignUpActivity
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class WelcomeActivity : AppCompatActivity(){

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        auth = FirebaseAuth.getInstance()

        val btnToLoginActivity = findViewById<Button>(R.id.btnToLoginActivity)
        val btnToSignUpActivity = findViewById<Button>(R.id.btnToSignupActivity)

        btnToLoginActivity.setOnClickListener {
            auth.signOut()
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        btnToSignUpActivity.setOnClickListener {
            intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }

    }

    private fun reload () {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


}