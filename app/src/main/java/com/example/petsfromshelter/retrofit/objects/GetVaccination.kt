package com.example.petsfromshelter.retrofit.objects

import com.example.petsfromshelter.retrofit.RetrofitClient
import com.example.petsfromshelter.retrofit.interfaces.GetVaccination

object GetVaccination {
    private val URL = "https://shelter-service.herokuapp.com/animal_vaccinations/"

    val getVaccinationList: GetVaccination
        get () = RetrofitClient.getClient(URL).create(GetVaccination::class.java)
}