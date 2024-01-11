package com.example.thematrix

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.thematrix.databinding.FragmentMatrixDetailBinding

class MatrixDetailFragment : Fragment() {

    private var _binding: FragmentMatrixDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatrixDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        // Retrieve data index from arguments
        val dataIndex = arguments?.getInt("dataIndex", -1)

        if (dataIndex != -1) {
            // Access arrays from resources
            val titles = resources.getStringArray(R.array.movie_titles)
            val releaseYears = resources.getStringArray(R.array.release_years)
            val descriptions = resources.getStringArray(R.array.descriptions)
            val posterImages = resources.obtainTypedArray(R.array.poster_images)
            val bannerImages = resources.obtainTypedArray(R.array.banner_images)

            // Set data to ViewBinding
            binding.titleDetailTextView.text = titles[dataIndex!!]
            binding.releaseYearTextView.text = releaseYears[dataIndex]
            binding.synopsisTextView.text = descriptions[dataIndex]

            // Set a dummy rating for now (replace with actual data)
            binding.ratingBar.rating = 4.0f

            // Load or set the poster and banner images
            binding.posterImageView.setImageResource(posterImages.getResourceId(dataIndex, -1))
            binding.bannerImageView.setImageResource(bannerImages.getResourceId(dataIndex, -1))

            // Customize the fragment to display other details as needed
        }
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}