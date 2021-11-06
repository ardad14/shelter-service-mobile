package com.example.petsfromshelter.models

data class User(
        var id: Int?,
        var name: String,
        var surname: String,
        var email: String,
        var phone: String,
        var password: String,
        var role: Boolean?,
        var shelterId: Int?
)
