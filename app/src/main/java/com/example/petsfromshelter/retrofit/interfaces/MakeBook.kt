package com.example.petsfromshelter.retrofit.interfaces

import com.example.petsfromshelter.models.Animal
import com.example.petsfromshelter.models.Bookings
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT

interface MakeBook {
    @PUT("https://shelter-service.herokuapp.com/update_animal/")
    fun makeBook(@Body requestBody: Animal): Call<Animal>;
}