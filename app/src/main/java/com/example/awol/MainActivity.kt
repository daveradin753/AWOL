package com.example.awol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.awol.fragments.MyAccountFragment
import com.example.awol.fragments.NavigationFragment
import com.example.awol.fragments.RestaurantListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var navMainMenu : BottomNavigationView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navMainMenu = findViewById(R.id.navMainMenu)
        auth = FirebaseAuth.getInstance()

        val myAccountFragment = MyAccountFragment()
        val navigationFragment = NavigationFragment()
        val restaurantListFragment = RestaurantListFragment()

        makeCurrentFragment(restaurantListFragment)

        navMainMenu.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.icMap -> {makeCurrentFragment(navigationFragment)}
                R.id.icRestaurant -> {makeCurrentFragment(restaurantListFragment)}
                R.id.icUser -> {makeCurrentFragment(myAccountFragment)}
            }
            true
        }


    }
    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flMainMenu, fragment)
            commit()
        }
    }
}