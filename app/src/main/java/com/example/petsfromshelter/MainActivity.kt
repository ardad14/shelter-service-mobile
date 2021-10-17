package com.example.petsfromshelter

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

    }

    fun signUp(view: View) {}

    fun registration(view: View) {
        val registrationActivity = Intent (this, RegistrationActivity::class.java)
        startActivity(registrationActivity)
    }
}