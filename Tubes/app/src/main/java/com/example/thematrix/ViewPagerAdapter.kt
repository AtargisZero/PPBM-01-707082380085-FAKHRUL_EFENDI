package com.example.thematrix

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2 // Adjust the count based on the number of fragments you have
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ActorFragment()
            1 -> ImagesFragment()
            else -> throw IllegalArgumentException("Invalid position $position")
        }
    }
}