package com.example.petsfromshelter.retrofit.interfaces

import com.example.petsfromshelter.entity.Shelter
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT

interface UpdateShelter {
    @PUT("https://shelter-service.herokuapp.com/update_shelter?id=2")
    fun updateShelter(@Body requestBody: Shelter): Call<Shelter>;
}