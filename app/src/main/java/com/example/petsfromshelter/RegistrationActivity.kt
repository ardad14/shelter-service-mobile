package com.example.petsfromshelter

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

import com.example.petsfromshelter.models.User
import com.example.petsfromshelter.retrofit.objects.Registration

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

        val user = User(
                null,
                name.text.toString(),
                surname.text.toString(),
                email.text.toString(),
                phone.text.toString(),
                password.text.toString(),
                true,
                null
        );

        val mainActivity = Intent (this, MainActivity::class.java)
        startActivity(mainActivity)

        val registrationService = Registration.registration;
        registrationService.registration(user).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {}

            override fun onResponse(call: Call<User>, response: Response<User>) {
                println(response.body());
                startActivity(mainActivity);
            }
        })


    }
}