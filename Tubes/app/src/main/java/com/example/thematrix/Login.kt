package com.example.thematrix

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var videoView: VideoView
    private var currentPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val emailEditText: EditText = findViewById(R.id.editTextEmailLogin)
        val passwordEditText: EditText = findViewById(R.id.editTextPasswordLogin)
        val loginButton: Button = findViewById(R.id.buttonLogin)
        val registerButton: Button = findViewById(R.id.buttonRegister)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Login success
                            val user: FirebaseUser? = auth.currentUser
                            MyToast.showCustomToast(
                                this,
                                getString(R.string.lgSuc_toast, user?.email ?: ""),
                                Toast.LENGTH_SHORT
                            )

                            // You can navigate to another screen or perform additional actions
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish() // Finish the login activity to prevent going back
                        } else {
                            // Login failed
                            MyToast.showCustomToast(
                                this,
                                "Login failed. ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            )
                        }
                    }
            } else {
                // Handle empty fields
                MyToast.showCustomToast(this, "Please fill in all fields", Toast.LENGTH_SHORT)
            }
        }

        registerButton.setOnClickListener {
            val intent=Intent(this@Login, Register::class.java)
            startActivity(intent)
        }

        // Find the VideoView by its ID
        videoView = findViewById(R.id.videoView)

        // Set the video file
        val videoPath = "android.resource://" + packageName + "/" + R.raw.video
        videoView.setVideoURI(Uri.parse(videoPath))

        // Start playing the video
        videoView.start()
        // Set OnCompletionListener to loop the video
        videoView.setOnCompletionListener { mp ->
            // Rewind the video to the beginning
            mp.seekTo(0)
            // Start the video again
            videoView.start()
        }
    }
    override fun onPause() {
        super.onPause()
        // Save the current position of the video
        currentPosition = videoView.currentPosition
        // Pause the video
        videoView.pause()
    }

    override fun onResume() {
        super.onResume()
        // Resume the video from the saved position
        videoView.seekTo(currentPosition)
        videoView.start()
    }
}