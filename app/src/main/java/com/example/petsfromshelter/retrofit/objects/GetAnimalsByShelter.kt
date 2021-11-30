package com.example.petsfromshelter.retrofit.objects

import com.example.petsfromshelter.retrofit.RetrofitClient
import com.example.petsfromshelter.retrofit.interfaces.GetAnimalsByShelter

object GetAnimalsByShelter {
    private val GET_URL = "https://shelter-service.herokuapp.com/animals/"

    val getAnimalsByShelter: GetAnimalsByShelter
        get() = RetrofitClient.getClient(GET_URL).create(GetAnimalsByShelter::class.java)
}