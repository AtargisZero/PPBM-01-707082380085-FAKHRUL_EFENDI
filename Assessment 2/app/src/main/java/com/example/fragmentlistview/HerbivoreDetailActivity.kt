package com.example.fragmentlistview

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import java.util.Locale

// HerbivoreDetailActivity.kt
class HerbivoreDetailActivity : OrientationActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_herbivore_detail)

        // Retrieve the animal name from the intent
        val animalName = intent.getStringExtra("animal_name")

        // Set the action bar title to the animal name
        supportActionBar?.title = animalName

        // Find views in the layout
        val carnivoreDetailTextView = findViewById<TextView>(R.id.detail_description)
        val imageView = findViewById<ImageView>(R.id.detail_image)

        // // Retrieve the title for the current animal from intent a.k.a animalName
        val titleTextView = findViewById<TextView>(R.id.detail_title)
        titleTextView.text = animalName

        // Retrieve the description for the current animal from strings.xml
        val descriptionResourceId = resources.getIdentifier("${animalName?.lowercase()}_desc", "string", packageName)
        val animalDescription = if (descriptionResourceId != 0) getString(descriptionResourceId) else "Description not available"

        // Set the description text
        carnivoreDetailTextView.text = animalDescription

        // Retrieve the image resource ID for the current animal
        val imageResourceId = resources.getIdentifier(animalName?.lowercase(Locale.getDefault()), "drawable", packageName)

        // Set the image resource for the ImageView
        imageView.setImageResource(imageResourceId)
    }
}