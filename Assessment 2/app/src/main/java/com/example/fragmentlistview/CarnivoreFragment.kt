package com.example.fragmentlistview

// Import statements for necessary classes
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.fragment.app.Fragment

class CarnivoreFragment : Fragment(R.layout.carnivore_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val images = arrayOf(
            R.drawable.lion,
            R.drawable.tiger,
            R.drawable.leopard,
            R.drawable.wolf
        )

        val carnivoreListView = view.findViewById<ListView>(R.id.carnivoreListView)

        val carnivoreAnimals = listOf("Lion", "Tiger", "Leopard", "Wolf")

        val adapter = CarnivoreAdapter(requireContext(), R.layout.carnivore_list_item, carnivoreAnimals, images)
        carnivoreListView.adapter = adapter

        carnivoreListView.setOnItemClickListener { _, _, position, _ ->
            val selectedAnimal = carnivoreAnimals[position]
            val selectedImage = images[position]
            openDetailActivity(selectedAnimal, selectedImage)
        }
    }
    // Function to open the detail activity with the selected animal's name and image
    private fun openDetailActivity(animalName: String, imageResourceId: Int) {
        val intent = Intent(requireContext(), CarnivoreDetailActivity::class.java)
        intent.putExtra("animal_name", animalName)
        intent.putExtra("image_resource_id", imageResourceId)
        startActivity(intent)
    }
}