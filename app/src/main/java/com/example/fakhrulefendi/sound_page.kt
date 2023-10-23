package com.example.fakhrulefendi

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.io.IOException

class sound_page : AppCompatActivity() {

    private lateinit var play_button: Button
    private lateinit var pause_button: Button
    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound_page)

        play_button = findViewById(R.id.play_button)
        pause_button = findViewById(R.id.pause_button)

        play_button.setOnClickListener {
            playAudio()
        }
        pause_button.setOnClickListener {
            pauseAudio()
        }
    }

    private fun playAudio() {
        var audioUrl = "https://www.bensound.com/bensound-music/bensound-ukulele.mp3"
        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)

        try {
            mediaPlayer!!.setDataSource(audioUrl)
            mediaPlayer!!.prepare()
            mediaPlayer!!.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        Toast.makeText(this,"Sound Playing", Toast.LENGTH_SHORT).show()
    }

    private fun pauseAudio() {
       if(mediaPlayer!!.isPlaying){
           mediaPlayer!!.stop()
           mediaPlayer!!.reset()
           mediaPlayer!!.release()
       }
        else{
            Toast.makeText(this, "Sound Stopped", Toast.LENGTH_SHORT).show()
        }
    }
}