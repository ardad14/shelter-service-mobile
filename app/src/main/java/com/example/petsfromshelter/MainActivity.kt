package com.example.petsfromshelter

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun signUp(view: View) {
        val email: EditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val password: EditText = findViewById<EditText>(R.id.editTextTextPassword)
        val db = FirebaseFirestore.getInstance()

        db.collection("user")
                .get().addOnSuccessListener {
                    it.forEach {
                        if (it.get("email").toString().equals(email.text.toString())) {
                            if (it.get("password").toString().equals(password.text.toString())) {
                                val shelterActivity = Intent (this, ShelterActivity::class.java)
                                startActivity(shelterActivity)
                            } else {
                                password.setBackgroundColor(Color.parseColor("#FF0000"))
                            }
                        }

                    }
                }
        email.setBackgroundColor(Color.parseColor("#FF0000"))

    }

    fun registration(view: View) {
        val registrationActivity = Intent (this, RegistrationActivity::class.java)
        startActivity(registrationActivity)
    }
}