package com.example.travelapp
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TravelPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3 // Adjust based on the number of destinations/categories.

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AdventureFragment()
            1 -> RelaxationFragment()
            2 -> CulturalFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}