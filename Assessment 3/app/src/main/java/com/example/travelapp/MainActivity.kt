package com.example.travelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)

        val imageList = ArrayList<SlideModel>() // Create image list

        imageList.add(SlideModel(R.drawable.adventure, "Explore thrilling adventures around the world."))
        imageList.add(SlideModel(R.drawable.relaxation, "Discover peaceful places for ultimate relaxation."))
        imageList.add(SlideModel(R.drawable.cultural, "Explore the rich cultural heritage of different regions."))

        val imageSlider = findViewById<ImageSlider>(R.id.slideImage)
        imageSlider.setImageList(imageList)



        val adapter = TravelPagerAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            // Customize tab names if needed
            tab.text = when (position) {
                0 -> "Adventure"
                1 -> "Relaxation"
                2 -> "Cultural"
                else -> throw IllegalArgumentException("Invalid position")
            }
        }.attach()
    }
}