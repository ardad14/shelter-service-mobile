package com.example.petsfromshelter.retrofit.objects

import com.example.petsfromshelter.retrofit.interfaces.GetAllShelters
import com.example.petsfromshelter.retrofit.RetrofitClient

object GetAllShelters {
    private val GET_URL = "https://shelter-service.herokuapp.com/shelters/"

    val sheltersRetrofitService: GetAllShelters
        get() = RetrofitClient.getClient(GET_URL).create(GetAllShelters::class.java)
}