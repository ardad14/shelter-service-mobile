package com.example.petsfromshelter.entity

import java.time.LocalDate

class AnimalVaccination (
    private var id: Int,
    private var animalId: Int,
    private var vaccineName: String,
    private var illnes: String,
    private var doze: Double,
    private var date: LocalDate
) {
}