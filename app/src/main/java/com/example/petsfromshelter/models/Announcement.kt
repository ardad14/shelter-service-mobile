package com.example.petsfromshelter.models

class Announcement (
    private var id: Int,
    private var name: String,
    private var description: String,
    private var moneyGoal: Int,
    private var status: String,
    private var shelterId: Int
) {
}