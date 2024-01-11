package com.example.thematrix

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.ncorti.slidetoact.SlideToActView

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val slideToAct = findViewById<SlideToActView>(R.id.slideToActView)
        val neoImageView = findViewById<ImageView>(R.id.neoImageView)

        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_to_act_animation)
        slideToAct.startAnimation(slideUp)

        val scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up)
        neoImageView.startAnimation(scaleUp)

        // Set listener for slide completion
        slideToAct.onSlideCompleteListener = object : SlideToActView.OnSlideCompleteListener {
            override fun onSlideComplete(view: SlideToActView) {
                navigateToMainMenu()
            }
        }
    }

    private fun navigateToMainMenu() {
        val intent = Intent(this@WelcomeActivity, Login::class.java)
        startActivity(intent)
        finish()
    }
}