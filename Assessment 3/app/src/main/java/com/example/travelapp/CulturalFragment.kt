package com.example.travelapp
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.travelapp.databinding.FragmentCulturalBinding

class CulturalFragment : Fragment() {

    private lateinit var binding: FragmentCulturalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCulturalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set content for Cultural Fragment
        binding.imageCultural.setImageResource(R.drawable.cultural)
        binding.textViewCulturalTitle.text = "Cultural Heritage"
        binding.textViewCulturalDescription.text = "Explore the rich cultural heritage of different regions."

        // Handle button click or any additional logic if needed
        binding.buttonExploreCultural.setOnClickListener {
            // Add your logic for button click, e.g., navigation or action
        }
    }
}
