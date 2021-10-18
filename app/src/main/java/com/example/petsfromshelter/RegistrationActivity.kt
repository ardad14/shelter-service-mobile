package com.example.petsfromshelter

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.math.log

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
    }


    fun registration(view: View) {
        val nameRegex = Regex("[a-zA-Z]{4,}")
        val phoneRegex = Regex("[+][0-9]{4,}")
        val emailRegex = Regex("\\w+@\\w+\\.\\w+")
        val passwordRegex = Regex("[a-zA-Z0-9]{4,}")

        val name: EditText = findViewById<EditText>(R.id.editTextTextPersonName)
        val surname: EditText = findViewById<EditText>(R.id.editTextTextPersonSurname)
        val phone: EditText = findViewById<EditText>(R.id.editTextPhone)
        val email: EditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val password: EditText = findViewById<EditText>(R.id.editTextTextPassword)


        if (!nameRegex.matches(name.text.toString())) {
            name.setBackgroundColor(Color.parseColor("#FF0000"))
            return
        }
        if(!nameRegex.matches(surname.text.toString())) {
            surname.setBackgroundColor(Color.parseColor("#FF0000"));
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
        if(!passwordRegex.matches(password.text.toString())) {
            password.setBackgroundColor(Color.parseColor("#FF0000"));
            return
        }

        val db = FirebaseFirestore.getInstance()
        val user = db.collection("user")

        user.add(mapOf(
                "name" to name.text.toString(),
                "surname" to surname.text.toString(),
                "phone" to phone.text.toString(),
                "email" to email.text.toString(),
                "password" to password.text.toString(),
                "role" to "user",
                "shelterId" to null
        ))

        val mainActivity = Intent (this, MainActivity::class.java)
        startActivity(mainActivity)
    }
}