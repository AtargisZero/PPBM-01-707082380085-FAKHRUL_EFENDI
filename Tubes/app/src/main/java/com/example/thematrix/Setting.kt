package com.example.thematrix

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.example.langguage.LanguageManager

class Setting : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var languageManager: LanguageManager
    private lateinit var matrixSpinnerSetting: Spinner
    private lateinit var spinnerButton: Button
    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_setting)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE)
        editor = sharedPreferences.edit()

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

        // Retrieve the current state of the rating from SharedPreferences
        val currentRating = sharedPreferences.getFloat("userRating", 3.5f)

        // Initialize other UI elements
        val ratingBar: RatingBar = findViewById(R.id.ratingBar)

        // Set the initial state of the RatingBar
        ratingBar.rating = currentRating

        // Handle rating changes
        ratingBar.setOnRatingBarChangeListener { _, rating, fromUser ->
            if (fromUser) {
                handleRatingChange(rating)
            }
        }

        // Initialize UI elements
        val toggleMusicSwitch: SwitchCompat = findViewById(R.id.toggleMusicSwitch)
        val volumeSeekBar: SeekBar = findViewById(R.id.volumeSeekBar)
        val languageRadioGroup: RadioGroup = findViewById(R.id.languageRadioGroup)

        // Retrieve the current state of settings
        val isMusicEnabled = sharedPreferences.getBoolean("musicEnabled", true)
        val volumeLevel = sharedPreferences.getInt("volumeLevel", 50)

        // Set the initial state of UI elements based on stored preferences
        toggleMusicSwitch.isChecked = isMusicEnabled
        volumeSeekBar.progress = volumeLevel

        languageManager = LanguageManager(this)
        // Set the initial state of language radio buttons
        languageRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.englishRadioButton -> languageManager.setLocale("en")
                R.id.bahasaRadioButton -> languageManager.setLocale("in")
                R.id.japanRadioButton -> languageManager.setLocale("ja")
                // Add more cases for other languages as needed
            }
            when (checkedId) {
                R.id.englishRadioButton -> showRestartDialog("en")
                R.id.bahasaRadioButton -> showRestartDialog("in")
                R.id.japanRadioButton -> showRestartDialog("ja")
                // Add more cases for other languages as needed
            }
        }

        // Handle toggle switch for background music
        toggleMusicSwitch.setOnCheckedChangeListener { _, isChecked ->
            handleToggleMusicSwitch(isChecked)
        }

        // Handle volume adjustment
        volumeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                handleVolumeAdjustment(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // No action needed
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // No action needed
            }
        })
        // Initialize the Matrix-themed Spinner
        matrixSpinnerSetting = findViewById(R.id.matrixSpinner)

        // Create an ArrayAdapter using the string array and a custom spinner layout
        val spinnerAdapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.matrix_options,
            R.layout.custom_spinner_item
        )

        // Specify the layout to use when the list of choices appears
        spinnerAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item)

        // Apply the adapter to the spinner
        matrixSpinnerSetting.adapter = spinnerAdapter

        // Set up item selection listener for the Matrix-themed Spinner
        matrixSpinnerSetting.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Do nothing here, handle the action on button click
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing here or add default behavior
            }
        }
        // Initialize the button for triggering actions
        spinnerButton = findViewById(R.id.spinnerButton)
        spinnerButton.setOnClickListener {
            // Get the selected Matrix-themed option from the spinner
            val selectedOption = matrixSpinnerSetting.selectedItem.toString()

            // Handle the selected Matrix-themed option
            handleMatrixSpinnerSelection(selectedOption)
        }
    }

// Function to handle actions based on Matrix-themed Spinner selection
    private fun handleMatrixSpinnerSelection(selectedOption: String) {
        when (selectedOption) {
            "Reload Matrix" -> {
                // Action: Show alert dialog to restart the app
                showRestartAlertDialog()
            }
            "Deja Vu Check" -> {
                // Action: Show alert dialog with content viewing on the current layout
                showContentAlertDialog()
            }
            "Agent Smith Encounter" -> {
                // Action: Show alert dialog like an emergency situation with a sound
                showEmergencyAlertDialog()
            }
            "Hack the Matrix" -> {
                // Action: Show snackbar telling the user that hacking is successful
                showCustomToast(getString(R.string.hack))
            }
            "Nebuchadnezzar Exploration" -> {
                // Action: Move to another activity
                startActivity(Intent(this, Play::class.java))
            }
            // Add more cases as needed
        }
    }

    // Function to handle rating changes
    private fun handleRatingChange(rating: Float) {
        // Save the rating to SharedPreferences
        editor.putFloat("userRating", rating)
        editor.apply()

        // Show a custom Toast indicating the user's rating
        MyToast.showRatingToast(this, rating)
    }

    // Function to handle toggle switch for background music
    private fun handleToggleMusicSwitch(isChecked: Boolean) {
        // Save the state to SharedPreferences
        editor.putBoolean("musicEnabled", isChecked)
        editor.apply()

        // Log statement to check the state of music control
        Log.d("SettingActivity", "Music Enabled: $isChecked")

        // Broadcast an intent to notify the MainActivity about the change
        val intent = Intent("com.example.thematrix.MUSIC_SETTINGS_CHANGED")
        sendBroadcast(intent)
    }

    // Function to handle volume adjustment
    private fun handleVolumeAdjustment(progress: Int) {
        // Save the volume level to SharedPreferences
        editor.putInt("volumeLevel", progress)
        editor.apply()

        // Log statement to check the volume level
        Log.d("SettingActivity", "Volume Level: $progress")

        // Broadcast an intent to notify the MainActivity about the volume change
        val volumeIntent = Intent("com.example.thematrix.VOLUME_CHANGED")
        volumeIntent.putExtra("volume", progress)
        sendBroadcast(volumeIntent)
    }

    private fun showRestartDialog(selectedLanguage: String) {
        val dialogView = layoutInflater.inflate(R.layout.custom_restart_dialog_langguage, null)

        val dialogTitle = dialogView.findViewById<TextView>(R.id.dialogTitle)
        val dialogMessage = dialogView.findViewById<TextView>(R.id.dialogMessage)
        val yesButton = dialogView.findViewById<Button>(R.id.yesButton)
        val noButton = dialogView.findViewById<Button>(R.id.noButton)

        // Use string resources
        dialogTitle.text = getString(R.string.rst_dlg)
        dialogMessage.text = getString(R.string.rst_txt)

        val alertDialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()
        yesButton.text = getString(R.string.rst)
        noButton.text = getString(R.string.cnc)

        yesButton.setOnClickListener {
            // Save the selected language to SharedPreferences
            editor.putString("selectedLanguage", selectedLanguage)
            editor.apply()
            // Restart the app by recreating the activity
            val intent = Intent(this, WelcomeActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
        noButton.setOnClickListener {
            // If the user chooses not to exit, dismiss the dialog
            alertDialog.dismiss()
        }
        alertDialog.show()
    }
    // Function to show an alert dialog for restarting the app
    private fun showRestartAlertDialog() {
        val dialogView = layoutInflater.inflate(R.layout.custom_restart_dialog_spinner, null)

        val dialogTitle = dialogView.findViewById<TextView>(R.id.dialogTitle)
        val dialogMessage = dialogView.findViewById<TextView>(R.id.dialogMessage)
        val yesButton = dialogView.findViewById<Button>(R.id.yesButton)
        val noButton = dialogView.findViewById<Button>(R.id.noButton)

        // Use string resources
        dialogTitle.text = getString(R.string.rst_spinner)
        dialogMessage.text = getString(R.string.txt_spinner)

        val alertDialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()
        yesButton.text = getString(R.string.rst)
        noButton.text = getString(R.string.cnc)

        yesButton.setOnClickListener {
            // Add a delay of 2 seconds (adjust as needed)
            val delayMillis = 2000L // 2 seconds
            showCustomToast(getString(R.string.tst_rst_spinner))
            Handler(Looper.getMainLooper()).postDelayed({
                // Restart the app by recreating the activity
                val intent = Intent(this, WelcomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }, delayMillis)
            alertDialog.dismiss()
        }
        noButton.setOnClickListener {
            // If the user chooses not to exit, dismiss the dialog
            alertDialog.dismiss()
            showCustomToast(getString(R.string.tst_spinner))
        }
        alertDialog.show()
    }

    // Function to show an alert dialog with content viewing on the current layout
    private fun showContentAlertDialog() {

    }

    // Function to show an alert dialog like an emergency situation with a sound
    private fun showEmergencyAlertDialog() {
        // Implement the logic to show an alert dialog like an emergency situation with a sound
        // You can use AlertDialog.Builder to create the dialog and play a sound
    }

    // Function to show a custom toast with a message
    private fun showCustomToast(message: String) {
        // Use your custom toast implementation
        MyToast.showCustomToast(this, message, Toast.LENGTH_SHORT)
    }
}