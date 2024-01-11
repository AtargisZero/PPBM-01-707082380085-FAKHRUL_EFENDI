package com.example.thematrix

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class Play : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val imageList = ArrayList<SlideModel>() // Create image list

        imageList.add(SlideModel(R.drawable.banner1, "The Matrix"))
        imageList.add(SlideModel(R.drawable.banner2, "The Matrix Reloaded"))
        imageList.add(SlideModel(R.drawable.banner3, "The Matrix Revolution"))
        imageList.add(SlideModel(R.drawable.banner4, "The Matrix Resurrection"))

        val imageSlider = findViewById<ImageSlider>(R.id.imageSlider)
        imageSlider.setImageList(imageList)

        // Load the ListFragment initially
        supportFragmentManager.beginTransaction().replace(R.id.container, MatrixListFragment()).commit()

        // Set up BottomNavigationView item click listener
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_movies -> {
                    // Load MoviesFragment
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MatrixListFragment())
                        .commit()
                    showToast("Matrix Movies Clicked")
                    true
                }
                R.id.action_actor -> {
                    startActivity(Intent(this@Play, ActorActivity::class.java))
                    true
                }
                R.id.action_about -> {
                    // Start About activity
                    startActivity(Intent(this@Play, About::class.java))
                    true
                }

                R.id.action_settings -> {
                    // Start Setting activity
                    startActivity(Intent(this@Play, Setting::class.java))
                    true
                }

                R.id.action_exit -> {
                    // Show exit confirmation dialog
                    showExitConfirmationDialog()
                    true
                }

                else -> false
            }
        }
    }

    private fun showToast(message: String) {
        MyToast.showCustomToast(this, message, Toast.LENGTH_SHORT)
    }

    private fun showExitConfirmationDialog() {
        val dialogView = layoutInflater.inflate(R.layout.custom_exit_dialog, null)

        val yesButton = dialogView.findViewById<Button>(R.id.yesButton)
        val noButton = dialogView.findViewById<Button>(R.id.noButton)

        val alertDialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        yesButton.setOnClickListener {
            // If the user chooses to exit, finish the activity
            finish()
            alertDialog.dismiss()
        }

        noButton.setOnClickListener {
            // If the user chooses not to exit, dismiss the dialog
            alertDialog.dismiss()
        }
        alertDialog.show()
    }
}