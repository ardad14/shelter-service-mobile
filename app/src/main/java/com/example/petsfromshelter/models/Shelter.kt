package com.example.petsfromshelter.models

import java.io.Serializable;

data class Shelter (
    var id: Int,
    var name: String,
    var email: String,
    var longitude: Double,
    var latitude: Double,
    var phone: String,
    var siteUrl: String
) : Serializable


