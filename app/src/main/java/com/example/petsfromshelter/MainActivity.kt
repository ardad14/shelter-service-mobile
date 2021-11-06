package com.example.petsfromshelter

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.petsfromshelter.models.User
import com.example.petsfromshelter.models.UserLoginForm
import com.example.petsfromshelter.retrofit.objects.Login

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
/*
        val shelterActivity = Intent (this, ShelterActivity::class.java)
        startActivity(shelterActivity)*/

    }

    fun signUp(view: View) {
        val email: EditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val password: EditText = findViewById<EditText>(R.id.editTextTextPassword)

        val user = UserLoginForm(
                email.text.toString(),
                password.text.toString()
        );
        val intent = Intent(this, ShelterMaps::class.java)
        val loginService = Login.login;
        loginService.login(user).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {}

            override fun onResponse(call: Call<User>, response: Response<User>) {
                println(response.body());
                startActivity(intent);
            }
        })


    }

    fun registration(view: View) {
        val registrationActivity = Intent (this, RegistrationActivity::class.java)
        startActivity(registrationActivity)
    }
}