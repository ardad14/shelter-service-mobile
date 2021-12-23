package com.example.petsfromshelter.models

import java.sql.Date

class AnimalVaccination (
    private var id: Int,
    private var animal_id: Int,
    private var vaccine_name: String,
    private var doze: Double,
    private var date: Date,
    private var disease: String
) {
    override fun toString(): String {
        return "\nНазва вакцини: '$vaccine_name',\nДата: $date,\nЗахворювання: '$disease'"
    }
}