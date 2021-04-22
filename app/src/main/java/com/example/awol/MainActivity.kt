package com.example.awol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.awol.fragments.MyAccountFragment
import com.example.awol.fragments.NavigationFragment
import com.example.awol.fragments.RestaurantListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navMainMenu : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navMainMenu = findViewById(R.id.navMainMenu)
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