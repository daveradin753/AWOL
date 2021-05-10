package com.example.awol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RestaurantDetail : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private val dataObjectRestaurant: MutableList<DataObjectRestaurant> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_detail)

        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        databaseReference = database.getReference("user").child(auth.uid!!).child("name")
        databaseReference.setValue("udin")

        val tvRestaurantDetailName = findViewById<TextView>(R.id.tvRestaurantDetailName)
        val tvRestaurantDetailAddress = findViewById<TextView>(R.id.tvRestaurantDetailAddress)
        val tvRestaurantDetailWorkingHour = findViewById<TextView>(R.id.tvRestaurantDetailWorkingHour)
        val ivRestaurantDetailBack = findViewById<ImageView>(R.id.ivRestaurantDetailBack)

        val name = intent.getStringExtra("restoName").toString()
        val address = intent.getStringExtra("restoAddress").toString()
        val workingHour = intent.getStringExtra("restoWorkingHour")

        tvRestaurantDetailName.text = name
        tvRestaurantDetailAddress.text = address
        tvRestaurantDetailWorkingHour.text = workingHour

    }


}