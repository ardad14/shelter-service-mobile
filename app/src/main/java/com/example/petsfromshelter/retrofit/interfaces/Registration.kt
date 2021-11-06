package com.example.petsfromshelter.retrofit.interfaces

import com.example.petsfromshelter.models.User
import com.example.petsfromshelter.models.UserLoginForm
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Registration {
    @POST("https://shelter-service.herokuapp.com/registration/")
    fun registration(@Body requestBody: User): Call<User>;
}