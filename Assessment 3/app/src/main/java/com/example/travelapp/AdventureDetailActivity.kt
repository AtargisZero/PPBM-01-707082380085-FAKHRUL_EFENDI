package com.example.travelapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.travelapp.databinding.ActivityAdventureDetailBinding

class AdventureDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdventureDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdventureDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve data or perform any necessary initialization
        val adventureTitle = "Adventure Destinations"
        val adventureDescription = "Explore thrilling adventures around the world."

        // Set data to UI elements
        binding.textViewAdventureDetailTitle.text = adventureTitle
        binding.textViewAdventureDetailDescription.text = adventureDescription

        // Additional data and UI elements can be set here based on your design
    }
}