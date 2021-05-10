package com.example.awol.login_signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.awol.MainActivity
import com.example.awol.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmailLogin = findViewById<EditText>(R.id.etEmailLogin)
        val etPasswordLogin = findViewById<EditText>(R.id.etPasswordLogin)

        auth = FirebaseAuth.getInstance()
        findViewById<TextView>(R.id.tvGoToSignUp).setOnClickListener {
            intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.btnLogin).setOnClickListener {
            var email = etEmailLogin.text.toString().trim()
            var password = etPasswordLogin.text.toString().trim()

            if (TextUtils.isEmpty(email)){
                etEmailLogin.error = "Email is required!"
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(password)){
                etPasswordLogin.error = "Password is required!"
                return@setOnClickListener
            }
            if (password.length < 8){
                etPasswordLogin.error = "Password must be more than 8 characters!"
                return@setOnClickListener
            }
            signIn(email, password)
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this,"Login Success", Toast.LENGTH_SHORT).show()
                        Log.d("LOGIN", "signInWithEmail:success")
                        intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }else {
                        Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
                        Log.w("LOGIN", "signInWithEmail:failure", it.exception)
                    }
                }
    }
    private fun reload () {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}