package com.example.fragmentlistview

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : OrientationActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load the HerbivoreFragment by default
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HerbivoreFragment()).commit()

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.nav_view)

        bottomNavigation.setOnItemSelectedListener { item ->
            val selectedFragment = when (item.itemId) {
                R.id.herbivore -> HerbivoreFragment()
                R.id.carnivore -> CarnivoreFragment()
                else -> return@setOnItemSelectedListener false
            }

            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()
            true
        }
    }
}
