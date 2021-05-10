package com.example.awol.login_signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.example.awol.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

private lateinit var tvSignUp : TextView
private lateinit var etFirstNameSignUp : EditText
private lateinit var etLastNameSignUp : EditText
private lateinit var etPasswordSignUp : EditText
private lateinit var etConfirmPasswordSignUp : EditText
private lateinit var etEmail : EditText
private lateinit var databaseRefrence : DatabaseReference
private lateinit var database: FirebaseDatabase
private lateinit var auth : FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        tvSignUp = findViewById(R.id.tvSignUp)
        etFirstNameSignUp = findViewById(R.id.etFirstNameSignUp)
        etLastNameSignUp = findViewById(R.id.etLastNameSignUp)
        etPasswordSignUp = findViewById(R.id.etPasswordSignUp)
        etConfirmPasswordSignUp = findViewById(R.id.etConfirmPasswordSignUp)
        etEmail = findViewById(R.id.etEmail)

        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        databaseRefrence = database.getReference("user").child(auth.uid!!).child("name")
        databaseRefrence.setValue("Dave")

    }
}