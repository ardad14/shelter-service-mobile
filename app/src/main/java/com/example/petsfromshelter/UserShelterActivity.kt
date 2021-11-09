package com.example.petsfromshelter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.petsfromshelter.fragments.PetsFragment
import com.example.petsfromshelter.fragments.ShelterInformationFragment
import com.example.petsfromshelter.models.Shelter
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserShelterActivity : AppCompatActivity() {

    private val petsFragment = PetsFragment()
    private val shelterInformationFragment = ShelterInformationFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_shelter)

        //replaceFragment(shelterInformationFragment)

       /* BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.pets -> {
                    replaceFragment(petsFragment)
                    true
                }
                R.id.shelter -> {
                    replaceFragment(shelterInformationFragment)
                    true
                }
                else -> false
            }
        }*/

        val rawData = intent.extras
        val shelter = rawData?.getSerializable("shelter") as Shelter


        val name: TextView = findViewById<TextView>(R.id.nameText)
        val phone: TextView = findViewById<TextView>(R.id.phoneText)
        val email: TextView = findViewById<TextView>(R.id.emailText)
        val siteUrl: TextView = findViewById<TextView>(R.id.urlText)

        name.text = shelter.name
        phone.text = shelter.phone
        email.text = shelter.email
        siteUrl.text = shelter.siteUrl

    }

    /*private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }*/
}