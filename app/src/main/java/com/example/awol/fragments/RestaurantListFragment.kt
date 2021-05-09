package com.example.awol.fragments

import android.os.Bundle
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


        val layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        val adapter = RestaurantListCustomAdapter(dataObjectRestaurant)
        val rvRestaurantList = view.findViewById<RecyclerView>(R.id.rvRestaurantList)

        rvRestaurantList.layoutManager = layoutManager
        rvRestaurantList.setHasFixedSize(true)
        rvRestaurantList.adapter = adapter
    }

    private fun getDataRestaurant() {
        database = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("restaurant")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (data in snapshot.children) {
                        val name = snapshot.child("name").value.toString()
                        val alamat = snapshot.child("alamat").value.toString()
                        val koordinat = snapshot.child("koordinat").value.toString()
                        val workingHour = snapshot.child("working hour").value.toString()
                        dataObjectRestaurant.add(DataObjectRestaurant(name, alamat, koordinat, workingHour))
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