package com.example.petsfromshelter.retrofit.objects

import com.example.petsfromshelter.retrofit.RetrofitClient

object GetOneShelter {
    private val GET_URL = "https://shelter-service.herokuapp.com/shelter_page?id=2"

    val GetOneShelter: GetOneShelter
        get() = RetrofitClient.getClient(GET_URL).create(GetOneShelter::class.java)
}