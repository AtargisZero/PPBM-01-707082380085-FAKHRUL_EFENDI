package com.example.fakhrulefendi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup

private fun RadioButton.isSelected(function: () -> Unit) {

}

class selection_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection_page)

        val sel_button = findViewById<Button>(R.id.select_button)
        val rad_herb = findViewById<RadioButton>(R.id.radio_herb)
        val rad_carn = findViewById<RadioButton>(R.id.radio_carn)

        rad_herb.setOnCheckedChangeListener { buttonView, isChecked ->
            sel_button.setOnClickListener {
                val intent = Intent(this, herbivore_page::class.java)
                startActivity(intent)
            }
        }
        rad_carn.setOnCheckedChangeListener { buttonView, isChecked ->
            sel_button.setOnClickListener {
                val intent = Intent(this, carnivore_page::class.java)
                startActivity(intent)
            }
        }
    }
}

