package com.example.thematrix

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var musicSettingsReceiver: BroadcastReceiver
    private lateinit var volumeChangeReceiver: BroadcastReceiver
    private lateinit var videoView: VideoView
    private var currentPosition: Int = 0

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

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

        // Initialize MediaPlayer with the background music
        mediaPlayer = MediaPlayer.create(this, R.raw.piano)
        mediaPlayer.isLooping = true // Loop the music

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE)

        // Start playing the background music based on user's preference
        if (sharedPreferences.getBoolean("musicEnabled", true)) {
            mediaPlayer.start()
        }
        // Button to navigate to the 'About' activity
        val playButton = findViewById<Button>(R.id.playButton)
        playButton.setOnClickListener {
            intent = Intent(this@MainActivity, Play::class.java)
            startActivity(intent)
        }

        // Button to navigate to the 'About' activity
        val aboutButton = findViewById<Button>(R.id.aboutButton)
        aboutButton.setOnClickListener {
            intent = Intent(this@MainActivity, About::class.java)
            startActivity(intent)
        }

        // Button to navigate to the 'Setting' activity
        val settingButton = findViewById<Button>(R.id.settingButton)
        settingButton.setOnClickListener {
            intent = Intent(this@MainActivity, Setting::class.java)
            startActivity(intent)
        }

        // Button to show a bottom sheet with Matrix content
        val matrixButton: Button = findViewById(R.id.matrixButton)
        matrixButton.setOnClickListener {
            showMatrixBottomSheet()
        }

        // Button to show a Snackbar with Matrix theme
        val showSnackbarButton = findViewById<Button>(R.id.showSnackbarButton)
        showSnackbarButton.setOnClickListener {
            showMatrixSnackbar(getString(R.string.welcomeMessage), getString(R.string.exploreAction))
        }

        // Button to exit the app
        val exitButton = findViewById<Button>(R.id.exitButton)
        exitButton.setOnClickListener {
            showExitConfirmationDialog()
        }

        // Register BroadcastReceiver to receive broadcasts about music settings changes
        musicSettingsReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                // Update music control based on new settings
                updateMusicControl()
            }
        }
        registerReceiver(musicSettingsReceiver, IntentFilter("com.example.thematrix.MUSIC_SETTINGS_CHANGED"),
            RECEIVER_NOT_EXPORTED
        )

        // Register BroadcastReceiver to receive broadcasts about volume changes
        volumeChangeReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == "com.example.thematrix.VOLUME_CHANGED") {
                    val newVolume = intent.getIntExtra("volume", 50) // Default value is 50
                    Log.d("MainActivity", "Received volume change broadcast: $newVolume")
                    updateMusicControl()
                }
            }
        }
        registerReceiver(volumeChangeReceiver, IntentFilter("com.example.thematrix.VOLUME_CHANGED"),
            RECEIVER_NOT_EXPORTED)
    }
    override fun onDestroy() {
        super.onDestroy()
        // Unregister the BroadcastReceivers
        unregisterReceiver(musicSettingsReceiver)
        unregisterReceiver(volumeChangeReceiver)
        // Release resources when the activity is destroyed
        mediaPlayer.release()
    }

    // Function to show a bottom sheet with Matrix content
    private fun showMatrixBottomSheet() {
        val bottomSheetFragment = MatrixBottomSheetFragment()
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }

    // Function to show a Snackbar with Matrix theme
    private fun showMatrixSnackbar(message: String, actionText: String) {
        // Create a Snackbar with a custom view
        val snackbar = Snackbar.make(
            findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        )

        // Customize Snackbar colors to match the Matrix theme
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(ContextCompat.getColor(this, R.color.matrixGreen)) // Matrix green color

        // Find the TextView in the Snackbar layout
        val textView = snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)

        // Set the text color of the Snackbar
        textView.setTextColor(ContextCompat.getColor(this, android.R.color.black)) // Black text color

        // Customize action button
        snackbar.setActionTextColor(ContextCompat.getColor(this, R.color.matrixBlack)) // Matrix blue color for action button text

        // Set the action for the Snackbar
        snackbar.setAction(actionText) {
            // Add your action logic here
            // This will be called when the action button is clicked

            // Show a Matrix-themed message indicating the choice made by the user using custom Toast
            val customToastMessage = getString(R.string.matrixProChoice, actionText)
            MyToast.showCustomToast(this, customToastMessage, Toast.LENGTH_SHORT)

            // You can add further logic based on the choice, if needed
        }
        // Show the Snackbar
        snackbar.show()
    }

    // Function to handle music control based on settings
    private fun updateMusicControl() {
        // Set the audio attributes for the MediaPlayer
        val audioAttributes = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .build()
        mediaPlayer.setAudioAttributes(audioAttributes)
        // Start or pause the music based on the current setting
        if (sharedPreferences.getBoolean("musicEnabled", true)) {
            // Get the volume level from SharedPreferences
            val volumeLevel = sharedPreferences.getInt("volumeLevel", 50)
            // Set the volume level for the MediaPlayer
            val maxVolume = 100
            val volume = 1.0f * volumeLevel / maxVolume
            mediaPlayer.setVolume(volume, volume)
            mediaPlayer.setOnCompletionListener { mp ->
                // Loop the video when it completes
                mp.start()
            }
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
            }
        } else {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
            }
        }
    }
    private fun showExitConfirmationDialog() {
        val dialogView = layoutInflater.inflate(R.layout.custom_exit_dialog, null)

        val dialogTitle = dialogView.findViewById<TextView>(R.id.dialogTitle)
        val dialogMessage = dialogView.findViewById<TextView>(R.id.dialogMessage)
        val yesButton = dialogView.findViewById<Button>(R.id.yesButton)
        val noButton = dialogView.findViewById<Button>(R.id.noButton)

        // Use string resources
        dialogTitle.text = getString(R.string.ext_conf)
        dialogMessage.text = getString(R.string.ext_txt)

        val alertDialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        yesButton.text = getString(R.string.yes)
        noButton.text = getString(R.string.no)

        yesButton.setOnClickListener {
            // If the user chooses to exit, finish the activity
            finishAffinity()
            alertDialog.dismiss()
        }

        noButton.setOnClickListener {
            // If the user chooses not to exit, dismiss the dialog
            alertDialog.dismiss()
        }
        alertDialog.show()
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