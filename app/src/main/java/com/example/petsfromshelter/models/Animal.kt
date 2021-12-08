package com.example.petsfromshelter.models

import java.io.Serializable;
import java.sql.Date

data class Animal (
        var id: Int,
        var name: String,
        var description: String,
        var gender: String,
        var age: String,
        var sterelized: Boolean,
        var imageUrl: String,
        var species: String,
        var shelterId: Int
) : Serializable