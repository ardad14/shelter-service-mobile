package com.example.petsfromshelter

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class CreateShelterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_shelter)

    }

    fun createShelter(view: View) {
        val nameRegex = Regex("[a-zA-Z]{4,}")
        val phoneRegex = Regex("[+][0-9]{4,}")
        val emailRegex = Regex("\\w+@\\w+\\.\\w+")

        val name: EditText = findViewById<EditText>(R.id.editTextShelterName)
        val longitude: EditText = findViewById<EditText>(R.id.editTextLongitude)
        val latitude: EditText = findViewById<EditText>(R.id.editTextLatitude)
        val phone: EditText = findViewById<EditText>(R.id.editTextPhone)
        val email: EditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val siteUrl: EditText = findViewById<EditText>(R.id.editTextSiteUrl)


        if (!nameRegex.matches(name.text.toString())) {
            name.setBackgroundColor(Color.parseColor("#FF0000"))
            return
        }
        if(!phoneRegex.matches(phone.text.toString())) {
            phone.setBackgroundColor(Color.parseColor("#FF0000"));
            return
        }
        if(!emailRegex.matches(email.text.toString())) {
            email.setBackgroundColor(Color.parseColor("#FF0000"));
            return
        }

        val db = FirebaseFirestore.getInstance()
        val shelter = db.collection("shelter")

        shelter.add(mapOf(
            "name" to name.text.toString(),
            "latitude" to latitude.text.toString(),
            "longitude" to longitude.text.toString(),
            "email" to email.text.toString(),
            "phone" to phone.text.toString(),
            "siteUrl" to siteUrl.text.toString()
        ))

        val shelterActivity = Intent (this, ShelterActivity::class.java)
        startActivity(shelterActivity)
    }
}