package com.example.petsfromshelter.retrofit.interfaces

import com.example.petsfromshelter.models.Shelter
import retrofit2.Call
import retrofit2.http.GET

interface GetOneShelter {
    @GET("https://shelter-service.herokuapp.com/shelter_page?id=2")
    fun GetOneShelter(): Call<Shelter>;
}