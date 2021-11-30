package com.example.petsfromshelter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.petsfromshelter.R
import com.example.petsfromshelter.models.Animal
import com.example.petsfromshelter.services.CustomRecyclerAdapter


/**
 * A simple [Fragment] subclass.
 * Use the [PetsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PetsFragment : Fragment() {
    private var animals: ArrayList<Animal>? = null

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            animals = it.getSerializable("animals") as ArrayList<Animal>?
        }
        val context = activity;
        val view = inflater.inflate(R.layout.fragment_pets, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CustomRecyclerAdapter(animals!!)

        return view
    }

}