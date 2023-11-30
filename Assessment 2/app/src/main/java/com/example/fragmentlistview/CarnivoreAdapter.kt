package com.example.fragmentlistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CarnivoreAdapter(context: Context, resource: Int, objects: List<String>, private val images: Array<Int>) :
    ArrayAdapter<String>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.carnivore_list_item, parent, false)

        val carnivoreItemTextView = view.findViewById<TextView>(R.id.carnivoreItemTextView)
        carnivoreItemTextView.text = getItem(position)
        val carnivoreImageView = view.findViewById<ImageView>(R.id.carn_img)

        // Bind data to views
        carnivoreItemTextView.text = getItem(position)
        carnivoreImageView.setImageResource(images[position])

        return view
    }
}