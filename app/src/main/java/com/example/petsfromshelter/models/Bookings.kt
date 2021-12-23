package com.example.petsfromshelter.models

import java.sql.Timestamp

data class Bookings(
        var animalId: Int,
        var date: Timestamp
)