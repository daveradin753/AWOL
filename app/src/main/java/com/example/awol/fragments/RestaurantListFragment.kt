package com.example.awol.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.awol.DataObjectRestaurant
import com.example.awol.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class RestaurantListFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private val dataObjectRestaurant: MutableList<DataObjectRestaurant> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        getDataRestaurant()

        val layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        val adapter = RestaurantListCustomAdapter(dataObjectRestaurant)
        val rvRestaurantList = view.findViewById<RecyclerView>(R.id.rvRestaurantList)

        rvRestaurantList.layoutManager = layoutManager
        rvRestaurantList.setHasFixedSize(true)
        rvRestaurantList.adapter = adapter

    }

    private fun getDataRestaurant() {
        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("restaurants")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    dataObjectRestaurant.clear()
                    for (data in snapshot.children) {
                        val name = data.child("name").value.toString()
                        val alamat = data.child("alamat").value.toString()
                        val koordinat = data.child("koordinat").value.toString()
                        val workingHour = data.child("working hour").value.toString()
                        val images = data.child("image").child("restaurant image").value.toString()
                        dataObjectRestaurant.add(DataObjectRestaurant(name, alamat, koordinat, workingHour, images))
//                        Log.e("Database", "Read Success $name")

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            RestaurantListFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}