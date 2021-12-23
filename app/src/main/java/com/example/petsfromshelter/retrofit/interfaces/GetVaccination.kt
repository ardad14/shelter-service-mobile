package com.example.petsfromshelter.retrofit.interfaces

import com.example.petsfromshelter.models.AnimalVaccination
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetVaccination {
    @GET("https://shelter-service.herokuapp.com/animal_vaccinations/")
    fun getVaccinationList(@Query("animalId") int: Int): Call<ArrayList<AnimalVaccination>>
}