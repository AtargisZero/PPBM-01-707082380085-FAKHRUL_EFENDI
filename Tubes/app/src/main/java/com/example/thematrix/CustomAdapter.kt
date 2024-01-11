package com.example.thematrix

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat

class CustomAdapter(private val context: Context, private val titles: Array<String>) : BaseAdapter() {

    override fun getCount(): Int = titles.size

    override fun getItem(position: Int): Any = titles[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)

        val title = getItem(position) as String

        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        titleTextView.text = title
        titleTextView.setTextColor(ContextCompat.getColor(context, R.color.greenText))
        titleTextView.setTypeface(null, Typeface.BOLD)

        return view
    }
}