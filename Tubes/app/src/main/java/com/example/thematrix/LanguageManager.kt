package com.example.langguage

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import java.util.Locale

class LanguageManager(private val context: Context) {

    fun setLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration()
        config.locale = locale

        val resources = context.resources
        resources.updateConfiguration(config, resources.displayMetrics)

        // Save the selected language to shared preferences
        saveLanguageToPrefs(languageCode)
    }

    private fun saveLanguageToPrefs(languageCode: String) {
        val editor = getSharedPreferences().edit()
        editor.putString("language", languageCode)
        editor.apply()
    }

    private fun getSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    }
}