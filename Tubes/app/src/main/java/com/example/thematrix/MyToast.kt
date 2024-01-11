package com.example.thematrix

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast

object MyToast {

    fun showCustomToast(context: Context, message: String, duration: Int) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout: View = inflater.inflate(R.layout.custom_toast_layout, null)

        val text: TextView = layout.findViewById(R.id.toastMessage)
        text.text = message

        val toast = Toast(context)
        toast.duration = duration
        toast.view = layout
        toast.show()
    }

    fun showRatingToast(context: Context, rating: Float) {
        val message = "User Rating: $rating"
        showCustomToast(context, message, Toast.LENGTH_SHORT)
    }
}
