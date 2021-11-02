package com.example.petsfromshelter.retrofit.interfaces

import com.example.petsfromshelter.entity.Shelter
import retrofit2.Call
import retrofit2.http.GET

interface GetShelter {
    @GET("https://shelter-service.herokuapp.com/shelters/")
    fun getShelterList(): Call<ArrayList<Shelter>>;
}