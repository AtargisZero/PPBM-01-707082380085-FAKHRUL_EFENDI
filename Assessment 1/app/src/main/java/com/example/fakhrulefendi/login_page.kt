package com.example.fakhrulefendi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class login_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        val sb_button = findViewById<Button>(R.id.submit_button)
        sb_button.setOnClickListener {
            val intent = Intent(this, selection_page::class.java)
            startActivity(intent)
        }
        val reg_txt = findViewById<TextView>(R.id.regis_text)
        reg_txt.setOnClickListener {
            val intent = Intent(this, registration_page::class.java)
            startActivity(intent)
        }
    }
}