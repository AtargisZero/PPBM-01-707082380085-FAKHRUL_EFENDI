package com.example.travelapp
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.travelapp.databinding.FragmentRelaxationBinding

class RelaxationFragment : Fragment() {

    private lateinit var binding: FragmentRelaxationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRelaxationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set content for Relaxation Fragment
        binding.imageRelaxation.setImageResource(R.drawable.relaxation)
        binding.textViewRelaxationTitle.text = "Relaxation Spots"
        binding.textViewRelaxationDescription.text = "Discover peaceful places for ultimate relaxation."

        // Handle button click or any additional logic if needed
        binding.buttonExploreRelaxation.setOnClickListener {
            // Add your logic for button click, e.g., navigation or action
        }
    }
}