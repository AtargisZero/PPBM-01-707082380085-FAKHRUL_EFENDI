package com.example.travelapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.travelapp.databinding.FragmentAdventureBinding

class AdventureFragment : Fragment() {

    private var _binding: FragmentAdventureBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdventureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Access the UI elements using the binding
        binding.imageAdventure.setImageResource(R.drawable.adventure)
        binding.textViewAdventureTitle.text = "Adventure Destinations"
        binding.textViewAdventureDescription.text = "Explore thrilling adventures around the world."

        binding.buttonExploreAdventure.setOnClickListener {
            val intent = Intent(requireContext(), AdventureDetailActivity::class.java).apply {
                // Add more data as needed
            }
            startActivity(intent)
        }

        // Show Bottom Sheet when the button is clicked
        binding.showBottomSheetButton.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
