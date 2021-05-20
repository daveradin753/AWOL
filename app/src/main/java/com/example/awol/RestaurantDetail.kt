package com.example.awol

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class RestaurantDetail : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var auth: FirebaseAuth
    lateinit var gsReference: StorageReference
    private val dataObjectRestaurant: MutableList<DataObjectRestaurant> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_detail)

        val tvRestaurantDetailName = findViewById<TextView>(R.id.tvRestaurantDetailName)
        val tvRestaurantDetailAddress = findViewById<TextView>(R.id.tvRestaurantDetailAddress)
        val tvRestaurantDetailWorkingHour = findViewById<TextView>(R.id.tvRestaurantDetailWorkingHour)
        val ivRestaurantDetail = findViewById<ImageView>(R.id.ivRestaurantDetail)

        val name = intent.getStringExtra("restoName").toString()
        val address = intent.getStringExtra("restoAddress").toString()
        val workingHour = intent.getStringExtra("restoWorkingHour").toString()
        val images = intent.getStringExtra("restoImage").toString()

        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

//        gsReference = FirebaseStorage.getInstance().getReferenceFromUrl(images)
        gsReference = FirebaseStorage.getInstance().getReference("restaurants/$name/$name image.jpg")
        val localFile = File.createTempFile(name, "jpg")
        gsReference.getFile(localFile)
                .addOnSuccessListener {
                    val bitmap: Bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                    ivRestaurantDetail.setImageBitmap(bitmap)
                }

        tvRestaurantDetailName.text = name
        tvRestaurantDetailAddress.text = address
        tvRestaurantDetailWorkingHour.text = workingHour

    }


}