package com.example.thematrix

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ActorAdapter(private val context: Context, private val actorData: List<Actor>) :
    RecyclerView.Adapter<ActorAdapter.ActorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cardView =
            inflater.inflate(R.layout.card_actor, parent, false) as androidx.cardview.widget.CardView
        return ActorViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val actor = actorData[position]
        holder.bind(actor)
    }

    override fun getItemCount(): Int = actorData.size

    inner class ActorViewHolder(cardView: androidx.cardview.widget.CardView) :
        RecyclerView.ViewHolder(cardView) {
        private val imageView = cardView.findViewById<ImageView>(R.id.actorImageView)
        private val nameTextView = cardView.findViewById<TextView>(R.id.actorNameTextView)
        private val roleTextView = cardView.findViewById<TextView>(R.id.actorRoleTextView)
        private val infoTextView = cardView.findViewById<TextView>(R.id.actorInfoTextView)

        fun bind(actor: Actor) {
            imageView.setImageResource(actor.imageResId)
            nameTextView.text = actor.name
            roleTextView.text = actor.role
            infoTextView.text = actor.additionalInfo
        }
    }
}