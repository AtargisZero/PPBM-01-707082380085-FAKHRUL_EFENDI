package com.example.thematrix

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MatrixBottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_matrix_bottom_sheet, container, false)

        // Find the Red Pill button by its ID and set its click listener
        val redPillButton = view.findViewById<Button>(R.id.RedPill)
        redPillButton.setOnClickListener {
            // Handle the click for the Red Pill
            // For now, let's finish the current activity (closing the app)
            activity?.finish()
        }

        // Find the Blue Pill button by its ID and set its click listener
        val bluePillButton = view.findViewById<Button>(R.id.BluePill)
        bluePillButton.setOnClickListener {
            // Handle the click for the Blue Pill
            // Dismiss the bottom sheet
            dismiss()
            // Add a Toast message
            context?.let {
                MyToast.showCustomToast(it, "You chose the Blue Pill!", Toast.LENGTH_SHORT)
            }
        }
        return view
    }
}