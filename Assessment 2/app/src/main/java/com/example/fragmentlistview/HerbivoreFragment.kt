package com.example.fragmentlistview

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

// HerbivoreFragment.kt
class HerbivoreFragment : Fragment(R.layout.herbivore_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val image = arrayOf(
            R.drawable.elephant,
            R.drawable.giraffe,
            R.drawable.zebra,
            R.drawable.horse
        )

        val herbivoreListView = view.findViewById<ListView>(R.id.herbivoreListView)

        val herbivoreAnimals = arrayOf("Elephant", "Giraffe", "Zebra", "Horse")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, herbivoreAnimals)
        herbivoreListView.adapter = adapter

        herbivoreListView.setOnItemClickListener { _, _, position, _ ->
            val selectedAnimal = herbivoreAnimals[position]
            val selectedImage = image[position]
            openDetailActivity(selectedAnimal, selectedImage)
        }
    }

    private fun openDetailActivity(animalName: String, imageResourceId: Int) {
        val intent = Intent(requireContext(), HerbivoreDetailActivity::class.java)
        intent.putExtra("animal_name", animalName)
        intent.putExtra("image_resource_id", imageResourceId)
        startActivity(intent)
    }
}