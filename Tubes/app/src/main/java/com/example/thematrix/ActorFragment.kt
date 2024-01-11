package com.example.thematrix

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ActorFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var videoView: VideoView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_actor, container, false)

        videoView = view.findViewById(R.id.backgroundVideoView)
        val videoUri = Uri.parse("android.resource://" + requireContext().packageName + "/" + R.raw.video) // Replace with your video resource
        videoView.setVideoURI(videoUri)
        videoView.setOnPreparedListener { mp: MediaPlayer ->
            mp.isLooping = true
        }
        videoView.start()


        recyclerView = view.findViewById(R.id.actorRecyclerView)

        // Initialize ActorAdapter with actorList
        val actorList = listOf(
            Actor(
                requireContext().resources.getStringArray(R.array.actor_names)[0],
                R.drawable.neo,
                requireContext().resources.getStringArray(R.array.actor_roles)[0],
                requireContext().resources.getStringArray(R.array.actor_additional_info)[0]
            ),
            Actor(
                requireContext().resources.getStringArray(R.array.actor_names)[1],
                R.drawable.carrie, // Replace with the actual image resource ID
                requireContext().resources.getStringArray(R.array.actor_roles)[1],
                requireContext().resources.getStringArray(R.array.actor_additional_info)[1]
            ),
            Actor(
                requireContext().resources.getStringArray(R.array.actor_names)[2],
                R.drawable.morpheus, // Replace with the actual image resource ID
                requireContext().resources.getStringArray(R.array.actor_roles)[2],
                requireContext().resources.getStringArray(R.array.actor_additional_info)[2]
            ),
            Actor(
                requireContext().resources.getStringArray(R.array.actor_names)[3],
                R.drawable.smith, // Replace with the actual image resource ID
                requireContext().resources.getStringArray(R.array.actor_roles)[3],
                requireContext().resources.getStringArray(R.array.actor_additional_info)[3]
            ),
            Actor(
                requireContext().resources.getStringArray(R.array.actor_names)[4],
                R.drawable.cypher, // Replace with the actual image resource ID
                requireContext().resources.getStringArray(R.array.actor_roles)[4],
                requireContext().resources.getStringArray(R.array.actor_additional_info)[4]
            ),
            Actor(
                requireContext().resources.getStringArray(R.array.actor_names)[5],
                R.drawable.oracle, // Replace with the actual image resource ID
                requireContext().resources.getStringArray(R.array.actor_roles)[5],
                requireContext().resources.getStringArray(R.array.actor_additional_info)[5]
            ),
            Actor(
                requireContext().resources.getStringArray(R.array.actor_names)[6],
                R.drawable.niobe, // Replace with the actual image resource ID
                requireContext().resources.getStringArray(R.array.actor_roles)[6],
                requireContext().resources.getStringArray(R.array.actor_additional_info)[6]
            ),
            Actor(
                requireContext().resources.getStringArray(R.array.actor_names)[7],
                R.drawable.seraph, // Replace with the actual image resource ID
                requireContext().resources.getStringArray(R.array.actor_roles)[7],
                requireContext().resources.getStringArray(R.array.actor_additional_info)[7]
            ),
            Actor(
                requireContext().resources.getStringArray(R.array.actor_names)[8],
                R.drawable.persephone, // Replace with the actual image resource ID
                requireContext().resources.getStringArray(R.array.actor_roles)[8],
                requireContext().resources.getStringArray(R.array.actor_additional_info)[8]
            ),
            Actor(
                requireContext().resources.getStringArray(R.array.actor_names)[9],
                R.drawable.link, // Replace with the actual image resource ID
                requireContext().resources.getStringArray(R.array.actor_roles)[9],
                requireContext().resources.getStringArray(R.array.actor_additional_info)[9]
            ),
            // Add more actors as needed
        )

        val adapter = ActorAdapter(requireContext(), actorList)
        recyclerView.adapter = adapter

        // Set a layout manager (for example, LinearLayoutManager)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }
}