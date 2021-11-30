package com.example.petsfromshelter.retrofit.interfaces

import com.example.petsfromshelter.models.Animal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetAnimalsByShelter {
    @GET("https://shelter-service.herokuapp.com/animals/{id}/")
    fun getAnimalsList(@Path("id") int: Int): Call<ArrayList<Animal>>
}