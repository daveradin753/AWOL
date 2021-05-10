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
import com.example.awol.DataObjectUsers
import com.example.awol.MainActivity
import com.example.awol.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    private lateinit var tvSignUp : TextView
    private lateinit var etFirstNameSignUp : EditText
    private lateinit var etLastNameSignUp : EditText
    private lateinit var etPasswordSignUp : EditText
    private lateinit var etConfirmPasswordSignUp : EditText
    private lateinit var etEmail : EditText
//    private lateinit var btnSignUp: Button
    private lateinit var databaseRefrence : DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        tvSignUp = findViewById(R.id.tvSignUp)
        etFirstNameSignUp = findViewById(R.id.etFirstNameSignUp)
        etLastNameSignUp = findViewById(R.id.etLastNameSignUp)
        etPasswordSignUp = findViewById(R.id.etPasswordSignUp)
        etConfirmPasswordSignUp = findViewById(R.id.etConfirmPasswordSignUp)
        etEmail = findViewById(R.id.etEmail)

        auth = FirebaseAuth.getInstance()
        val currentUserID = auth.uid

        findViewById<Button>(R.id.btnSignUp).setOnClickListener {

        }



//        createNewUser(currentUserID, )


    }
    fun writeNewUser(userId: String, name: String, email: String) {
        val user = DataObjectUsers(name, email)

        database = FirebaseDatabase.getInstance()

        databaseRefrence = database.getReference("users").child(auth.uid!!)
        databaseRefrence.setValue(user)

    }

    fun createNewUser (userId: String, name: String, email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    writeNewUser(userId, name, email)
                    Log.d(SIGN_UP, "createUserWithEmail:success")
                    intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(SIGN_UP, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    final val SIGN_UP = "SIGN UP"

}