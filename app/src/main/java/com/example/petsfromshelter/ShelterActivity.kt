package com.example.petsfromshelter

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore

class ShelterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shelter)

        val name: EditText = findViewById<EditText>(R.id.nameText)
        val longitude: EditText = findViewById<EditText>(R.id.longitudeText)
        val latitude: EditText = findViewById<EditText>(R.id.latitudeText)
        val phone: EditText = findViewById<EditText>(R.id.phoneText)
        val email: EditText = findViewById<EditText>(R.id.emailText)
        val siteUrl: EditText = findViewById<EditText>(R.id.urlText)

        val db = FirebaseFirestore.getInstance()

        db.collection("shelter")
                .get().addOnSuccessListener {
                    it.forEach {
                        if (it.get("name").toString().equals("Pets Friendly")) {
                            name.text.insert(0, it.get("name").toString())
                            longitude.text.insert(0, it.get("longitude").toString())
                            latitude.text.insert(0, it.get("latitude").toString())
                            phone.text.insert(0, it.get("phone").toString())
                            email.text.insert(0, it.get("email").toString())
                            siteUrl.text.insert(0, it.get("siteUrl").toString())
                        }

                    }
                }


    }

    fun editShelter(view: View) {
        /*val nameRegex = Regex("[a-zA-Z]{4,}")
        val phoneRegex = Regex("[+][0-9]{4,}")
        val emailRegex = Regex("\\w+@\\w+\\.\\w+")*/

        val name: EditText = findViewById<EditText>(R.id.nameText)
        val longitude: EditText = findViewById<EditText>(R.id.longitudeText)
        val latitude: EditText = findViewById<EditText>(R.id.latitudeText)
        val phone: EditText = findViewById<EditText>(R.id.phoneText)
        val email: EditText = findViewById<EditText>(R.id.emailText)
        val siteUrl: EditText = findViewById<EditText>(R.id.urlText)

        /*if (!nameRegex.matches(name.text.toString())) {
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
        }*/

        val db = FirebaseFirestore.getInstance()
        val shelter = db.collection("shelter").document("qJaxsgth1IyxdknGEjmk")

        shelter.set(mapOf(
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