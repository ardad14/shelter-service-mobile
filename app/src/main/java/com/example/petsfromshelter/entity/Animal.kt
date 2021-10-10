package com.example.petsfromshelter.entity

import java.time.LocalDate

class Animal constructor (
    private var id: Int,
    private var name: String,
    private var breed: String,
    private var birth: LocalDate,
    private var weight: Double,
    private var image: String
) {

}