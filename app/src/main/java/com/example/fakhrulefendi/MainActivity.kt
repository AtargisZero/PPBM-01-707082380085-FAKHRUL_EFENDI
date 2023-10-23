package com.example.fakhrulefendi

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val st_button = findViewById<Button>(R.id.setting_button)
        val lg_button = findViewById<Button>(R.id.login_button)
        val ab_button = findViewById<Button>(R.id.about_button)
        val ex_button = findViewById<Button>(R.id.exit_button)
        val rt_button = findViewById<Button>(R.id.rating_button)
        lg_button.setOnClickListener {
            val intent = Intent(this, login_page::class.java)
            startActivity(intent)
        }
        st_button.setOnClickListener {
            val intent = Intent(this, setting_page::class.java)
            startActivity(intent)
        }
        ab_button.setOnClickListener {
            val intent = Intent(this, about_page::class.java)
            startActivity(intent)
        }
        rt_button.setOnClickListener {
            val intent = Intent(this, rating_page::class.java)
            startActivity(intent)
        }
        ex_button.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Do You Want to Exit?")
            builder.setTitle("Confirm Exit")
            builder.setCancelable(false)
            builder.setPositiveButton("Yes"){
                dialog, which -> finish()
            }
            builder.setNegativeButton("No"){
                dialog, which -> dialog.cancel()
            }
        val alertDialog = builder.create()
            alertDialog.show()
        }
    }
}