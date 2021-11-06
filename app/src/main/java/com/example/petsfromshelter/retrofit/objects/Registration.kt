package com.example.petsfromshelter.retrofit.objects

import com.example.petsfromshelter.retrofit.RetrofitClient
import com.example.petsfromshelter.retrofit.interfaces.Registration

object Registration {
    private val REGISTRATION_URL = "https://shelter-service.herokuapp.com/registration/"

    val registration: Registration
        get () = RetrofitClient.getClient(REGISTRATION_URL).create(Registration::class.java)
}