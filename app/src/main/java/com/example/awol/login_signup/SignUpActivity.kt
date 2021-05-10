package com.example.awol.login_signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.awol.R
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var database = FirebaseDatabase.getInstance().reference
        database.setValue("yuk dicoba")
    }
}