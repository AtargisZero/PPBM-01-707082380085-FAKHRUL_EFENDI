package com.example.thematrix

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment

class MatrixListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_matrix, container, false)

        val titles = resources.getStringArray(R.array.movie_titles)
        resources.getStringArray(R.array.release_years)
        resources.getStringArray(R.array.descriptions)

        val customAdapter = CustomAdapter(requireContext(), titles)
        val customListView: ListView = view.findViewById(R.id.customListView)
        customListView.adapter = customAdapter

        customListView.setOnItemClickListener { _, _, position, _ ->
            val fragment = MatrixDetailFragment()
            val bundle = Bundle()
            bundle.putInt("dataIndex", position)
            fragment.arguments = bundle

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}