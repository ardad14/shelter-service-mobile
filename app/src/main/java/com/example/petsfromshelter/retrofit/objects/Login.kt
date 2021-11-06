package com.example.petsfromshelter.retrofit.objects

import com.example.petsfromshelter.retrofit.RetrofitClient
import com.example.petsfromshelter.retrofit.interfaces.Login

object Login {
    private val LOGIN_URL = "https://shelter-service.herokuapp.com/login/"

    val login: Login
        get () = RetrofitClient.getClient(LOGIN_URL).create(Login::class.java)
}