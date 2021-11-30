package com.example.petsfromshelter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.view.ViewGroup
import android.widget.TextView
import com.example.petsfromshelter.R
import com.example.petsfromshelter.models.Shelter

/**
 * A simple [Fragment] subclass.
 * Use the [ShelterInformationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShelterInformationFragment : Fragment() {
    private var shelter: Shelter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            shelter = it.getSerializable("shelter") as Shelter?
        }

        val view = inflater.inflate(R.layout.fragment_shelter_information, container, false)
        val name: TextView = view.findViewById<TextView>(R.id.nameText)
        val phone: TextView = view.findViewById<TextView>(R.id.phoneText)
        val email: TextView = view.findViewById<TextView>(R.id.emailText)
        val siteUrl: TextView = view.findViewById<TextView>(R.id.urlText)

        name.text = shelter?.name
        phone.text = shelter?.phone
        email.text = shelter?.email
        siteUrl.text = shelter?.siteUrl
        return view
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }*/
}