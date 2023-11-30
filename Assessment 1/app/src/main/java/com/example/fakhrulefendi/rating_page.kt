package com.example.fakhrulefendi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast

class rating_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating_page)

        val rt_bar = findViewById<RatingBar>(R.id.rating_bar)
        val rt_button = findViewById<Button>(R.id.rating_button)
        val result = findViewById<TextView>(R.id.rate_result)

        rt_bar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            result.text = rating.toString()
            when (ratingBar.rating.toInt()){
                     1 -> result.text = "Very Bad"
                     2 -> result.text = "Bad"
                     3 -> result.text = "Good"
                     4 -> result.text = "Very Good"
                     5 -> result.text = "Amazing"
                     else -> result.text = " "
            }
        }

        rt_button.setOnClickListener {
            val pesan = rt_bar.rating.toString()
            Toast.makeText(this@rating_page,"Rating is: "+pesan, Toast.LENGTH_SHORT).show()
        }

    }
}