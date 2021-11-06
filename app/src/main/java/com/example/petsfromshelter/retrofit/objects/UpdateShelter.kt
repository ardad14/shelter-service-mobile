package com.example.petsfromshelter.retrofit.objects

import com.example.petsfromshelter.retrofit.RetrofitClient
import com.example.petsfromshelter.retrofit.interfaces.UpdateShelter

object UpdateShelter {

    private val UPDATE_URL = "https://shelter-service.herokuapp.com/update_shelter?id=2"

    val updateShelter: UpdateShelter
        get () = RetrofitClient.getClient(UPDATE_URL).create(UpdateShelter::class.java)
}