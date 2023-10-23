package com.example.fakhrulefendi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class setting_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_page)
        val lg_button = findViewById<Button>(R.id.lang_button)
        lg_button.setOnClickListener {
            val intent = Intent (this, language_page::class.java)
            startActivity(intent)
        }
    }
}