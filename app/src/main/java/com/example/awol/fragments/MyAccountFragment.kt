package com.example.awol.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.awol.DataObjectRestaurant
import com.example.awol.MainActivity
import com.example.awol.R
import com.example.awol.login_signup.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class MyAccountFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var gsRefrence : StorageReference
    private lateinit var ivProfilePicture : ImageView
    private lateinit var tvProfileName : TextView
    private lateinit var tvProfileEmail : TextView
    private lateinit var tvProfileDescription : TextView
    private lateinit var btnLogout : Button
    private lateinit var ivFaq : ImageView
    private var profilePicture : Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivProfilePicture = view.findViewById(R.id.ivProfilePicture)
        tvProfileName = view.findViewById(R.id.tvProfileName)
        tvProfileEmail = view.findViewById(R.id.tvProfileEmail)

        auth = FirebaseAuth.getInstance()
        val currentUID = auth.uid.toString()

        ivProfilePicture.setOnClickListener {
            val gallery = Intent (Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 1)
        }

        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("users").child(currentUID)
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val name = snapshot.child("name").value.toString()
                    val email = snapshot.child("email").value.toString()

                    tvProfileName.setText(name)
                    tvProfileEmail.setText(email)

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        val btnLogout = view.findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            auth.signOut()
            startActivity(intent)
        }
        databaseReference = database.getReference("users").child(currentUID).child("image")
        databaseReference.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    ivProfilePicture.setImageURI(Uri.parse(snapshot.value.toString()))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == 1) {
            profilePicture = data?.data
            auth = FirebaseAuth.getInstance()
            var currentUID = auth.uid.toString()
            var file = Uri.fromFile(File(profilePicture.toString()))
            var gsReference = FirebaseStorage.getInstance().getReference("users/$currentUID")
            var uploadTask = gsReference.putFile(file)
            uploadTask.addOnSuccessListener {
                var profileStorageURL = it.storage.downloadUrl.toString()
                database = FirebaseDatabase.getInstance()
                databaseReference = database.getReference("users").child(currentUID)
                databaseReference.child("image").setValue(profileStorageURL)
            }
        }
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            MyAccountFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
    final val ACCOUNT = "ACCOUNT"
}