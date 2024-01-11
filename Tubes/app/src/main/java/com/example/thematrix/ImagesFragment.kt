package com.example.thematrix

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ImagesFragment : Fragment() {

    // Replace this list with your actual list of image resources or URLs
    private val imageList = listOf(
        R.drawable.gambar1,
        R.drawable.gambar2,
        R.drawable.gambar3,
        R.drawable.gambar4,
        R.drawable.gambar5,
        R.drawable.gambar6,
        R.drawable.gambar7,
        R.drawable.gambar8,
        R.drawable.gambar9,
        R.drawable.gambar10,
        // Add more images as needed
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_images, container, false)

        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing) // Adjust this value as needed
        // Set up RecyclerView to display images
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.addItemDecoration(ItemDecoration(spacingInPixels))
        recyclerView.layoutManager = GridLayoutManager(context, 1) // Adjust spanCount as needed
        recyclerView.adapter = ImageAdapter(imageList)
        return view
    }
}