package com.example.petsfromshelter.retrofit.objects

import com.example.petsfromshelter.retrofit.RetrofitClient
import com.example.petsfromshelter.retrofit.interfaces.MakeBook

object MakeBook {
    private val URL = "https://shelter-service.herokuapp.com/update_animal/"

    val makeBook: MakeBook
        get () = RetrofitClient.getClient(URL).create(MakeBook::class.java)
}