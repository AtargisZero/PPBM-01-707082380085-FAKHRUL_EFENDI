package com.example.thematrix

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class About : AppCompatActivity() {
    private lateinit var videoView: VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val back:Button = findViewById(R.id.backButton)
        back.setOnClickListener {
            finish()
        }

        // Find the VideoView by its ID
        videoView = findViewById(R.id.videoView)

        // Set the video file
        val videoPath = "android.resource://" + packageName + "/" + R.raw.video
        videoView.setVideoURI(Uri.parse(videoPath))

        // Start playing the video
        videoView.start()

        // Set a completion listener to loop the video
        videoView.setOnCompletionListener { mp ->
            // Restart the video when it completes
            mp.start()
        }
    }
}