package com.example.petsfromshelter.retrofit.interfaces

import com.example.petsfromshelter.models.User
import com.example.petsfromshelter.models.UserLoginForm
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Login {
    @POST("https://shelter-service.herokuapp.com/login/")
    fun login(@Body requestBody: UserLoginForm): Call<User>;
}