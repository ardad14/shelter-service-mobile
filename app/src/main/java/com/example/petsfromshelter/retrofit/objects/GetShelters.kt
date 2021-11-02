package com.example.petsfromshelter.retrofit.objects

import com.example.petsfromshelter.retrofit.interfaces.GetShelter
import com.example.petsfromshelter.retrofit.RetrofitClient
import com.example.petsfromshelter.retrofit.interfaces.UpdateShelter

object GetShelters {
    private val GET_URL = "https://shelter-service.herokuapp.com/shelters/"

    val sheltersRetrofitService: GetShelter
        get() = RetrofitClient.getClient(GET_URL).create(GetShelter::class.java)
}